package knb.bunuyesene.features.detail.repositories

import knb.bunuyesene.features.common.domain.entities.TarifItem

interface TarifDetayRepository {

    suspend fun getTarifDetay(id: Long): Result<TarifItem>
    suspend fun addFavori(tarifId: Long)
    suspend fun removeFavori(tarifId: Long)

}