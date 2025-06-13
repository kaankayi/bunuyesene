package knb.bunuyesene.features.favorites.domain

import knb.bunuyesene.features.common.domain.entities.TarifItem

interface FavoriTarifRepository {
    suspend fun getAllFavoriTarifler(): Result<List<TarifItem>>
    suspend fun addFavori(tarifId: Long)
    suspend fun removeFavori(tarifId: Long)
}