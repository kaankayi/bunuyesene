package knb.bunuyesene.features.search.data.datasources

import knb.bunuyesene.features.common.domain.entities.TarifItem

interface TarifAraLocalDataSource {
    suspend fun araTarifByText(query: String): List<TarifItem>
}