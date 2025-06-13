package knb.bunuyesene.features.detail.data.datasources

import knb.bunuyesene.features.common.data.database.daos.FavoriTarifDao
import knb.bunuyesene.features.common.data.database.daos.TarifDao
import knb.bunuyesene.features.common.domain.entities.TarifItem

class TarifDetayLocalDataSourceImpl(
    private val tarifDao: TarifDao,
    private val favoriTarifDao: FavoriTarifDao
): TarifDetayLocalDataSource {

    override suspend fun getTarifDetay(id: Long): TarifItem? {
        return tarifDao.getTarifById(id)
    }

    override suspend fun saveTarif(tarif: TarifItem) {
        tarifDao.insertTarif(tarif)
    }

    override suspend fun addFavori(tarifId: Long) {
        favoriTarifDao.addFavori(tarifId)
    }

    override suspend fun removeFavori(tarifId: Long) {
        favoriTarifDao.removeFavori(tarifId)
    }

    override suspend fun isFavori(tarifId: Long): Boolean {
        return favoriTarifDao.isFavori(tarifId)
    }
}