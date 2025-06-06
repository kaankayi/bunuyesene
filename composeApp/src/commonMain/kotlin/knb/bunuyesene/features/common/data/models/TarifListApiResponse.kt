package knb.bunuyesene.features.common.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TarifListApiResponse(
    @SerialName("meals")
    val meals: List<TarifApiItem>
)