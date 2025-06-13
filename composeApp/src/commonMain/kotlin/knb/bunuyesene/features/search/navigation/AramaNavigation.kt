package knb.bunuyesene.features.search.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import knb.bunuyesene.features.app.data.Ekran
import knb.bunuyesene.features.search.ui.AramaRoute

fun NavController.navigateToArama(navOptions: NavOptions? = null) {
    navigate(Ekran.Arama.route)
}

fun NavGraphBuilder.aramaNavGraph(
    navigateToDetay: (Long) -> Unit,
    onBackPress: () -> Unit
) {

    composable(Ekran.Arama.route) {
        AramaRoute(
            navigateToDetay = navigateToDetay,
            onBackPress = onBackPress
        )
    }

}