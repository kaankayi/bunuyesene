package knb.bunuyesene.features.profile.data

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val myTarifCount: Int,
    val favoriTarifCount: Int,
    val followers: Int,
)