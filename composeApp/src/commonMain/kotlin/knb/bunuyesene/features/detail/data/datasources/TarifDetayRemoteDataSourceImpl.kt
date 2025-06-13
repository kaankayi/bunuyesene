package knb.bunuyesene.features.detail.data.datasources

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import knb.bunuyesene.features.common.data.api.BASE_URL
import knb.bunuyesene.features.common.data.models.TarifListApiResponse
import knb.bunuyesene.features.common.data.models.toTarif
import knb.bunuyesene.features.common.domain.entities.TarifItem

class TarifDetayRemoteDataSourceImpl(
    private val httpClient: HttpClient
): TarifDetayRemoteDataSource {

    override suspend fun getTarifDetay(id: Long): TarifItem? {
        val httpResponse = httpClient.get("${BASE_URL}lookup.php?i=$id")
        return httpResponse.body<TarifListApiResponse>().meals.firstOrNull()?.toTarif()
    }

}