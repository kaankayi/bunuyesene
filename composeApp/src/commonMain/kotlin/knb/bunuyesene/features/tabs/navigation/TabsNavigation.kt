package knb.bunuyesene.features.tabs.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import knb.bunuyesene.features.app.data.Ekran
import knb.bunuyesene.features.tabs.ui.TabsRoute

fun NavController.navigateToTabs(navOptions: NavOptions? = null) {
    navigate(Ekran.Tabs.route)
}

fun NavGraphBuilder.tabsNavGraph(
    navigateToDetay: (Long) -> Unit,
    navigateToArama: () -> Unit,
    tabNavController: NavHostController,
    isUserLoggedIn: () -> Boolean,
    openLoginBottomSheet: (() -> Unit) -> Unit,
    onLogout : () -> Unit
) {

    composable(Ekran.Tabs.route) {
        TabsRoute(
            tabNavController = tabNavController,
            navigateToDetay = navigateToDetay,
            isUserLoggedIn = isUserLoggedIn,
            openLoginBottomSheet = openLoginBottomSheet,
            onLogout = onLogout,
            navigateToArama = navigateToArama
        )
    }

}