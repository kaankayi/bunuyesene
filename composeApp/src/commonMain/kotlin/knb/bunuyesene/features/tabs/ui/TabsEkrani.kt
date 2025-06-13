package knb.bunuyesene.features.tabs.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import knb.bunuyesene.features.app.data.Ekran
import knb.bunuyesene.features.favorites.navigation.favoriNavGraph
import knb.bunuyesene.features.feed.navigation.feedNavGraph
import knb.bunuyesene.features.profile.navigation.profilNavGraph


val tabItems = listOf(
    Ekran.Anasayfa,
    Ekran.Favori,
    Ekran.Profil,
)

@Composable
fun TabsRoute(
    tabNavController: NavHostController,
    navigateToDetay: (Long) -> Unit,
    navigateToArama: () -> Unit,
    isUserLoggedIn: () -> Boolean,
    openLoginBottomSheet: (() -> Unit) -> Unit,
    onLogout: () -> Unit
) {

    TabsEkrani(
        tabNavController = tabNavController,
        navigateToDetay = navigateToDetay,
        isUserLoggedIn = isUserLoggedIn,
        openLoginBottomSheet = openLoginBottomSheet,
        onLogout = onLogout,
        navigateToArama = navigateToArama
    )

}


@Composable
fun TabsEkrani(
    tabNavController: NavHostController,
    navigateToDetay: (Long) -> Unit,
    navigateToArama: () -> Unit,
    isUserLoggedIn: () -> Boolean,
    openLoginBottomSheet: (() -> Unit) -> Unit,
    onLogout: () -> Unit
) {


    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.onPrimary
            ) {
                val navBackStackEntry by tabNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                tabItems.forEach { topLevelRoute ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == topLevelRoute.route } == true
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        ),
                        icon = {
                            val icon =
                                if (isSelected) topLevelRoute.selectedIcon else topLevelRoute.unselectedIcon
                            val color =
                                if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onBackground
                            icon?.let {
                                Icon(
                                    tint = color,
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(icon),
                                    contentDescription = topLevelRoute.route
                                )
                            }
                        },
                        label = { Text(stringResource(topLevelRoute.resourceId)) },
                        selected = isSelected,
                        onClick = {
                            tabNavController.navigate(topLevelRoute.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(tabNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            tabNavController,
            startDestination = Ekran.Anasayfa.route,
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            feedNavGraph(
                navigateToDetay = navigateToDetay,
                isUserLoggedIn = isUserLoggedIn,
                openLoginBottomSheet = openLoginBottomSheet,
                navigateToArama = navigateToArama
            )
            favoriNavGraph(
                navigateToDetay = navigateToDetay,
                isUserLoggedIn = isUserLoggedIn,
            )
            profilNavGraph(
                isUserLoggedIn = isUserLoggedIn,
                openLoginBottomSheet = openLoginBottomSheet,
                onLogout = onLogout
            )
        }
    }

}