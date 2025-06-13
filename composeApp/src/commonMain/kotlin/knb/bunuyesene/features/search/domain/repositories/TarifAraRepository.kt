package knb.bunuyesene.features.search.domain.repositories

import knb.bunuyesene.features.common.domain.entities.TarifItem

interface TarifAraRepository {
    suspend fun araTarifByText(query: String): Result<List<TarifItem>>
}