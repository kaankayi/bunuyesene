package knb.bunuyesene.features.login.ui

sealed class GirisState {

    object Idle: GirisState()
    object Loading: GirisState()
    object Success: GirisState()
    data class Error(val message: String): GirisState()

}