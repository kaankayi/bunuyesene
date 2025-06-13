package knb.bunuyesene.features.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import knb.bunuyesene.features.app.data.Ekran
import knb.bunuyesene.features.profile.ui.ProfilRoute

fun NavController.navigateToProfil(navOptions: NavOptions? = null) {
    navigate(Ekran.Profil.route)
}

fun NavGraphBuilder.profilNavGraph(
    isUserLoggedIn: () -> Boolean,
    openLoginBottomSheet: (() -> Unit) -> Unit,
    onLogout: () -> Unit
) {

    composable(Ekran.Profil.route) {
        ProfilRoute(
            isUserLoggedIn, openLoginBottomSheet, onLogout
        )
    }

}