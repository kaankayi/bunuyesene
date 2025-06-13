package knb.bunuyesene.features.feed.domain.repositories

import knb.bunuyesene.features.common.domain.entities.TarifItem

interface FeedRepository {

    suspend fun getTarifList(): Result<List<TarifItem>>
}