package knb.bunuyesene.features.feed.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import knb.bunuyesene.features.feed.domain.repositories.FeedRepository

class FeedViewModel(
    private val feedRepository: FeedRepository
) : ViewModel() {

    private var _feedUiState = MutableStateFlow(FeedUiState())
    val feedUiState = _feedUiState.asStateFlow()

    init {
        viewModelScope.launch {
            getTarifList()
        }
    }

    private suspend fun getTarifList() {
        val tarifList = feedRepository.getTarifList()
        if (tarifList.isSuccess) {
            _feedUiState.value = _feedUiState.value.copy(
                tarifList = tarifList.getOrDefault(emptyList()),
                tarifListIsLoading = false
            )
        } else {
            _feedUiState.update {
                it.copy(
                    tarifListError = tarifList.exceptionOrNull()?.message,
                    tarifListIsLoading = false
                )
            }
        }
    }

}