package knb.bunuyesene.features.search.ui

import knb.bunuyesene.features.common.domain.entities.TarifItem

data class AramaEkraniState(
    val idle: Boolean = true,
    val success: Boolean = false,
    val error: String? = null,
    val results: List<TarifItem> = emptyList()
)