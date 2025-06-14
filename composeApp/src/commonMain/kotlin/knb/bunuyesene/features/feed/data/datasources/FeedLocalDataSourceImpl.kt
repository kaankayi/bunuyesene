package knb.bunuyesene.features.feed.data.datasources

import knb.bunuyesene.features.common.data.database.daos.TarifDao
import knb.bunuyesene.features.common.domain.entities.TarifItem

class FeedLocalDataSourceImpl(
    private val tarifDao: TarifDao
): FeedLocalDataSource {
    override suspend fun getTarifList(): List<TarifItem> {
        return tarifDao.getAllTarifler()
    }

    override suspend fun saveTarifList(tarif: List<TarifItem>) {
        tarifDao.insertTarifBulk(tarif)
    }
}