package knb.bunuyesene.features.feed.data.datasources

import knb.bunuyesene.features.common.domain.entities.TarifItem

interface FeedRemoteDataSource {
    suspend fun getTarifList(): List<TarifItem>
}