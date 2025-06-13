package knb.bunuyesene.features.detail.ui

import knb.bunuyesene.features.common.domain.entities.TarifItem

data class TarifDetayUiState(
    val tarifDetay: TarifItem? = null,
    val tarifDetayIsLoading: Boolean = true,
    val tarifDetayError: String? = null,
)