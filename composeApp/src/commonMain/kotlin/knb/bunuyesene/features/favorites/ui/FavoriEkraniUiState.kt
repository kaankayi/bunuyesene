package knb.bunuyesene.features.favorites.ui
import knb.bunuyesene.features.common.domain.entities.TarifItem

data class FavoriEkraniUiState(
    val itemsList: List<TarifItem>? = null,
    val itemsListIsLoading: Boolean = true,
    val itemsListError: String? = null,
)