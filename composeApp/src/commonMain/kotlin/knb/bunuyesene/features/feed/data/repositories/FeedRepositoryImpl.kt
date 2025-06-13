package knb.bunuyesene.features.feed.data.repositories

import knb.bunuyesene.features.common.domain.entities.TarifItem
import knb.bunuyesene.features.feed.domain.repositories.FeedRepository
import knb.bunuyesene.features.feed.data.datasources.FeedLocalDataSource
import knb.bunuyesene.features.feed.data.datasources.FeedRemoteDataSource

class FeedRepositoryImpl(
    private val feedLocalDataSource: FeedLocalDataSource,
    private val feedRemoteDataSource: FeedRemoteDataSource,
): FeedRepository {

    override suspend fun getTarifList(): Result<List<TarifItem>> {
        return try {
            val recipeListCache = feedLocalDataSource.getTarifList()
            val count = recipeListCache.count()
            return if (count > 0) {
                Result.success(recipeListCache)
            } else {
                val tarifListApiResponse = feedRemoteDataSource.getTarifList()
                feedLocalDataSource.saveTarifList(tarifListApiResponse)
                Result.success(tarifListApiResponse)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}