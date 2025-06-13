package knb.bunuyesene.features.detail.data.datasources
import knb.bunuyesene.features.common.domain.entities.TarifItem

interface TarifDetayRemoteDataSource {

    suspend fun getTarifDetay(id: Long): TarifItem?

}