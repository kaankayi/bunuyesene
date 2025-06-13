package knb.bunuyesene.features.favorites.data

import knb.bunuyesene.features.common.domain.entities.TarifItem
import knb.bunuyesene.features.favorites.domain.FavoriTarifRepository

class FavoriTarifRepositoryImpl(
    private val favoriTarifLocalDataSource: FavoriTarifLocalDataSource
): FavoriTarifRepository {
    override suspend fun getAllFavoriTarifler(): Result<List<TarifItem>> {
        return try {
            val list = favoriTarifLocalDataSource.getAllFavoriTarifler()
            return Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override suspend fun addFavori(tarifId: Long) {
        favoriTarifLocalDataSource.addFavori(tarifId)
    }
    override suspend fun removeFavori(tarifId: Long) {
        favoriTarifLocalDataSource.removeFavori(tarifId)
    }
}