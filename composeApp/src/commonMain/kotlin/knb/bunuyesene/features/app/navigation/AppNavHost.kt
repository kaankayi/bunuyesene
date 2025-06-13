package knb.bunuyesene.features.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import knb.bunuyesene.features.app.data.AppState
import knb.bunuyesene.features.app.data.Ekran
import knb.bunuyesene.features.detail.navigation.detayNavGraph
import knb.bunuyesene.features.search.navigation.aramaNavGraph
import knb.bunuyesene.features.tabs.navigation.tabsNavGraph

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    appState: AppState,
    startDestination: String = Ekran.Tabs.route,
    isUserLoggedIn: () -> Boolean,
    openLoginBottomSheet: (() -> Unit) -> Unit,
    onLogout: () -> Unit
) {

    val navController = appState.navController

    val tabNavController = rememberNavController()

    NavHost(navController, startDestination = startDestination) {

        tabsNavGraph(
            tabNavController = tabNavController,
            navigateToDetay = {
                appState.navigateToDetay(it)
            },
            isUserLoggedIn = isUserLoggedIn,
            openLoginBottomSheet = openLoginBottomSheet,
            onLogout = onLogout,
            navigateToArama = appState::navigateToArama
        )

        aramaNavGraph(
            navigateToDetay = {
                appState.navigateToDetay(it)
            },
            onBackPress = appState::navigateBack
        )

        detayNavGraph(
            onBackClick = appState::navigateBack,
            isUserLoggedIn = isUserLoggedIn,
            openLoginBottomSheet = openLoginBottomSheet,
        )
    }


}