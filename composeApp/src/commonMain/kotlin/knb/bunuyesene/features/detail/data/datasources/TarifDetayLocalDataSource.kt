package knb.bunuyesene.features.detail.data.datasources

import knb.bunuyesene.features.common.domain.entities.TarifItem

interface TarifDetayLocalDataSource {

    suspend fun getTarifDetay(id: Long): TarifItem?
    suspend fun saveTarif(tarif: TarifItem)
    suspend fun addFavori(tarifId: Long)
    suspend fun removeFavori(tarifId: Long)
    suspend fun isFavori(tarifId: Long): Boolean

}