package knb.bunuyesene.features.feed.data.datasources

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import knb.bunuyesene.features.common.data.api.BASE_URL
import knb.bunuyesene.features.common.data.models.TarifListApiResponse
import knb.bunuyesene.features.common.data.models.toTarif
import knb.bunuyesene.features.common.domain.entities.TarifItem

class FeedRemoteDataSourceImpl(
    private val httpClient: HttpClient
): FeedRemoteDataSource {
    override suspend fun getTarifList(): List<TarifItem> {
        val allMeals = mutableListOf<TarifItem>()

        for (char in 'a'..'z') {
            val response = httpClient.get("${BASE_URL}search.php?f=$char")
            val mealsResponse = response.body<TarifListApiResponse>()
            mealsResponse.meals?.mapNotNull { it.toTarif() }?.let { allMeals.addAll(it) }
        }

        return allMeals
    }
}
