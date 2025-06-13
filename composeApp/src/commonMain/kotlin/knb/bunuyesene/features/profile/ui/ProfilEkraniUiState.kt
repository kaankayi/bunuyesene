package knb.bunuyesene.features.profile.ui

import knb.bunuyesene.features.profile.data.User


data class ProfilEkraniUiState(
    val userInfo: User? = null,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
)