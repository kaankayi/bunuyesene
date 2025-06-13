package knb.bunuyesene.features.favorites.data

import knb.bunuyesene.features.common.domain.entities.TarifItem

interface FavoriTarifLocalDataSource {
    suspend fun getAllFavoriTarifler(): List<TarifItem>
    suspend fun addFavori(tarifId: Long)
    suspend fun removeFavori(tarifId: Long)
}