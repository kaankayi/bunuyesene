package knb.bunuyesene.features.feed.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import knb.bunuyesene.features.app.data.Ekran
import knb.bunuyesene.features.feed.ui.FeedRoute

fun NavController.navigateToFeed(navOptions: NavOptions? = null) {
    navigate(Ekran.Anasayfa.route)
}

fun NavGraphBuilder.feedNavGraph(
    navigateToDetay: (Long) -> Unit,
    isUserLoggedIn: () -> Boolean,
    openLoginBottomSheet: (() -> Unit) -> Unit,
    navigateToArama: () -> Unit,
) {

    composable(Ekran.Anasayfa.route) {
        FeedRoute(
            navigateToDetay = navigateToDetay,
            navigateToArama = navigateToArama
        )
    }

}