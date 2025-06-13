package knb.bunuyesene.features.detail.data.repositories

import knb.bunuyesene.features.common.domain.entities.TarifItem
import knb.bunuyesene.features.detail.data.datasources.TarifDetayLocalDataSource
import knb.bunuyesene.features.detail.data.datasources.TarifDetayRemoteDataSource
import knb.bunuyesene.features.detail.repositories.TarifDetayRepository

class TarifDetayRepositoryImpl(
    private val tarifDetayLocalDataSource: TarifDetayLocalDataSource,
    private val tarifDetayRemoteDataSource: TarifDetayRemoteDataSource
): TarifDetayRepository {

    override suspend fun getTarifDetay(id: Long): Result<TarifItem> {
        return try {
            val tarifDetayCache = tarifDetayLocalDataSource.getTarifDetay(id)
            return if (tarifDetayCache != null) {
                val isFav = tarifDetayLocalDataSource.isFavori(tarifId = id)
                Result.success(tarifDetayCache.copy(
                    isFavori = isFav
                ))
            } else {
                val tarifDetayApiResponse = tarifDetayRemoteDataSource.getTarifDetay(id)
                    ?: return Result.failure(Exception("Recipe not found!"))
                tarifDetayLocalDataSource.saveTarif(tarifDetayApiResponse)
                Result.success(tarifDetayApiResponse)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addFavori(tarifId: Long) {
        tarifDetayLocalDataSource.addFavori(tarifId)
    }

    override suspend fun removeFavori(tarifId: Long) {
        tarifDetayLocalDataSource.removeFavori(tarifId)
    }
}