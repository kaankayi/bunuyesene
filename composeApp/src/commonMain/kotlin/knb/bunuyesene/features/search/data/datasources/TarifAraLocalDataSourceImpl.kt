package knb.bunuyesene.features.search.data.datasources

import knb.bunuyesene.features.common.data.database.daos.TarifDao
import knb.bunuyesene.features.common.domain.entities.TarifItem

class TarifAraLocalDataSourceImpl(
    private val tarifDao: TarifDao
): TarifAraLocalDataSource {
    override suspend fun araTarifByText(query: String): List<TarifItem> {
        return tarifDao.araTarifByText(query)
    }
}