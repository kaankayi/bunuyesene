package knb.bunuyesene.features.feed.data.datasources

import knb.bunuyesene.features.common.domain.entities.TarifItem

interface FeedLocalDataSource {
    suspend fun getRecipesList(): List<TarifItem>
    suspend fun saveRecipesList(recipes: List<TarifItem>)
}