package knb.bunuyesene.features.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import knb.bunuyesene.features.detail.repositories.TarifDetayRepository

class TarifDetayViewModel(
    private val tarifDetayRepository: TarifDetayRepository
): ViewModel() {

    private var _detayUiState = MutableStateFlow(TarifDetayUiState())
    val detayUiState = _detayUiState.asStateFlow()

    private var _updateIsFavUiState = MutableStateFlow(TarifDetayUpdateIsFavUiState())
    val updateIsFavUiState = _updateIsFavUiState.asStateFlow()

    suspend fun getTarifDetay(id: Long) {
        viewModelScope.launch {
            val tarifDetayRes = tarifDetayRepository.getTarifDetay(id)
            if (tarifDetayRes.isSuccess) {
                _detayUiState.value = _detayUiState.value.copy(
                    tarifDetay = tarifDetayRes.getOrNull(),
                    tarifDetayIsLoading = false
                )
            } else {
                _detayUiState.value = _detayUiState.value.copy(
                    tarifDetayError = tarifDetayRes.exceptionOrNull()?.message,
                    tarifDetayIsLoading = false
                )
            }
        }
    }

    fun updateIsFavori(tarifId: Long, isAdding: Boolean) {
        viewModelScope.launch {
            try {
                _updateIsFavUiState.value = _updateIsFavUiState.value.copy(isUpdating = true)

                if (isAdding) {
                    tarifDetayRepository.addFavori(tarifId)
                } else {
                    tarifDetayRepository.removeFavori(tarifId)
                }

                //refresh detail
                _detayUiState.value = _detayUiState.value.copy(
                    tarifDetay = _detayUiState.value.tarifDetay?.copy(isFavori = isAdding)
                )
                _updateIsFavUiState.value =
                    _updateIsFavUiState.value.copy(isSuccess = true, isUpdating = false)

            } catch (e: Exception) {
                _updateIsFavUiState.value =
                    _updateIsFavUiState.value.copy(error = e.message, isUpdating = false)
            }
        }
    }

}