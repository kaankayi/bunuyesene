package knb.bunuyesene.features.feed.ui


import knb.bunuyesene.features.common.domain.entities.TarifItem

data class FeedUiState(
    val tarifList: List<TarifItem>? = null,
    val tarifListIsLoading: Boolean = true,
    val tarifListError: String? = null,
)