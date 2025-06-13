package knb.bunuyesene.features.search.data.repositories

import knb.bunuyesene.features.common.domain.entities.TarifItem
import knb.bunuyesene.features.search.data.datasources.TarifAraLocalDataSource
import knb.bunuyesene.features.search.domain.repositories.TarifAraRepository

class TarifAraRepositoryImpl(
    private val tarifAraLocalDataSource: TarifAraLocalDataSource
): TarifAraRepository {
    override suspend fun araTarifByText(query: String): Result<List<TarifItem>> {
        return try {
            val resultList = tarifAraLocalDataSource.araTarifByText(query)
            Result.success(resultList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}