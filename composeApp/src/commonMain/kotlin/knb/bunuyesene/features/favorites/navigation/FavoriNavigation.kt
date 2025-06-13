package knb.bunuyesene.features.favorites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import knb.bunuyesene.features.app.data.Ekran
import knb.bunuyesene.features.favorites.ui.FavoriRoute

fun NavController.navigateToFavori(navOptions: NavOptions? = null) {
    navigate(Ekran.Favori.route)
}

fun NavGraphBuilder.favoriNavGraph(
    navigateToDetay: (Long) -> Unit,
    isUserLoggedIn: () -> Boolean,
) {

    composable(Ekran.Favori.route) {
        FavoriRoute(
            navigateToDetay = navigateToDetay,
            isUserLoggedIn = isUserLoggedIn,
        )
    }

}