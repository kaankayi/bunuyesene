package knb.bunuyesene.features.favorites.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import knb.bunuyesene.features.favorites.domain.FavoriTarifRepository

class FavoriEkraniViewModel(
    private val favoriTarifRepository: FavoriTarifRepository
): ViewModel() {

    private var _favoriEkraniUiState = MutableStateFlow(FavoriEkraniUiState())
    val favoriEkraniUiState = _favoriEkraniUiState.asStateFlow()

    init {
        viewModelScope.launch {
            getTarifList()
        }
    }

    suspend fun getTarifList() {
        val tarifList = favoriTarifRepository.getAllFavoriTarifler()
        if (tarifList.isSuccess) {
            _favoriEkraniUiState.value = _favoriEkraniUiState.value.copy(
                itemsList = tarifList.getOrDefault(emptyList()),
                itemsListIsLoading = false
            )
        } else {
            _favoriEkraniUiState.update {
                it.copy(
                    itemsListError = tarifList.exceptionOrNull()?.message,
                    itemsListIsLoading = false
                )
            }
        }
    }
}