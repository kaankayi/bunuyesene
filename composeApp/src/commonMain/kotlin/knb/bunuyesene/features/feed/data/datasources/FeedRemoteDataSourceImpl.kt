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
        val httpResponse = httpClient.get("${BASE_URL}search.php?f=b")
        val tarifListApiResponse = httpResponse.body<TarifListApiResponse>()
        return tarifListApiResponse.meals.mapNotNull {
            it.toTarif()
        }
    }
}