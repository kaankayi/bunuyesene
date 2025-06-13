package knb.bunuyesene.features.favorites.data

import knb.bunuyesene.features.common.data.database.daos.FavoriTarifDao
import knb.bunuyesene.features.common.domain.entities.TarifItem

class FavoriTarifLocalDataSourceImpl(
    private val favoriTarifDao: FavoriTarifDao
): FavoriTarifLocalDataSource {
    override suspend fun getAllFavoriTarifler(): List<TarifItem> {
        return favoriTarifDao.getAllFavoriTarifler()
    }
    override suspend fun addFavori(tarifId: Long) {
        favoriTarifDao.addFavori(tarifId)
    }
    override suspend fun removeFavori(tarifId: Long) {
        favoriTarifDao.removeFavori(tarifId)
    }
}