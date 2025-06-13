package knb.bunuyesene.features.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import knb.bunuyesene.features.common.domain.entities.TarifItem
import knb.bunuyesene.features.search.domain.repositories.TarifAraRepository

class AramaViewModel(
    private val tarifAraRepository: TarifAraRepository
): ViewModel() {

    private val _aramaText = MutableStateFlow("")
    val aramaText = _aramaText.asStateFlow()

    private val _aramaEkraniUiState = MutableStateFlow(AramaEkraniState())
    val aramaEkraniUiState = _aramaEkraniUiState.asStateFlow()

    init {
        triggerFetchItems()
    }

    private suspend fun fetchItems(query: String): List<TarifItem> {
        if (query.isEmpty()) return emptyList()
        val results = tarifAraRepository.araTarifByText(query)
        return results.getOrNull() ?: emptyList()
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun triggerFetchItems() = viewModelScope.launch {
        _aramaText
            .debounce(500)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                flow {
                    val results = fetchItems(query)
                    emit(results)
                }
            }
            .catch { error ->
                _aramaEkraniUiState.update {
                    it.copy(
                        idle = false,
                        error = error.message
                    )
                }
            }
            .collect { results ->

                _aramaEkraniUiState.update {
                    it.copy(
                        idle = false,
                        success = true,
                        results = results
                    )
                }
            }
    }

    fun onSearchQueryChanged(query: String) {
        _aramaText.value = query
    }
}