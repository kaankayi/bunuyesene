package knb.bunuyesene.features.feed.data.datasources

import knb.bunuyesene.features.common.domain.entities.TarifItem

interface FeedLocalDataSource {
    suspend fun getTarifList(): List<TarifItem>
    suspend fun saveTarifList(tarif: List<TarifItem>)
}