package knb.bunuyesene.features.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.savedstate.read
import knb.bunuyesene.features.app.data.Ekran
import knb.bunuyesene.features.detail.ui.DetayRoute

const val TARIF_ID_ARG = "tarifId"

fun NavController.navigateToDetay(id: Long, navOptions: NavOptions? = null) {
    navigate(Ekran.Detay.route.replace("$TARIF_ID_ARG={$TARIF_ID_ARG}", "$TARIF_ID_ARG=$id"))
}

fun NavGraphBuilder.detayNavGraph(
    onBackClick: () -> Unit,
    isUserLoggedIn: () -> Boolean,
    openLoginBottomSheet: (() -> Unit) -> Unit,
) {

    composable(Ekran.Detay.route,
        arguments = listOf(
            navArgument(TARIF_ID_ARG) {
                type = NavType.LongType
            }
        )
    ) {
        val tarifId = it.arguments?.read { getLong(TARIF_ID_ARG) } ?: 0
        DetayRoute(
            tarifId,
            isUserLoggedIn = isUserLoggedIn,
            openLoginBottomSheet = openLoginBottomSheet,
            onBackClick = onBackClick
        )
    }

}