package knb.bunuyesene

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import knb.bunuyesene.features.app.data.rememberAppState
import knb.bunuyesene.features.app.navigation.AppNavHost
import knb.bunuyesene.features.designSystem.theme.BunuYeseneTheme
import knb.bunuyesene.features.login.ui.GirisEkraniModalBottomSheet
import knb.bunuyesene.features.login.ui.GirisViewModel


@Composable
@Preview
fun App(
    girisViewModel: GirisViewModel = koinViewModel()
) {


    BunuYeseneTheme {

        KoinContext {

            val navController = rememberNavController()
            val appState = rememberAppState(
                navController,
                scope = rememberCoroutineScope(),
                appPreferences = koinInject()
            )

            var showLoginBottomSheet by remember {
                mutableStateOf(false)
            }

            val isLoggedIn by appState.isLoggedIn.collectAsState()

            val isUserLoggedIn: () -> Boolean = {
                isLoggedIn
            }

            var loginCallback : () -> Unit by remember {
                mutableStateOf({})
            }

            val openLoginBottomSheet: (() -> Unit) -> Unit = { laterCallback ->
                showLoginBottomSheet = true
                loginCallback = laterCallback
            }

            val onLoginSuccess: () -> Unit = {
                println("onLoginSuccess")
                showLoginBottomSheet = false
                appState.updateIsLoggedIn(true)
                girisViewModel.resetState()
                loginCallback()
            }

            val onLogout: () -> Unit = {
                appState.onLogout()
                girisViewModel.resetState()
            }

            val onCloseSheet: () -> Unit = {
                showLoginBottomSheet = false
                girisViewModel.resetState()
            }

            GirisEkraniModalBottomSheet(
                girisViewModel = girisViewModel,
                showBottomSheet = showLoginBottomSheet,
                onClose = onCloseSheet,
                onLoginSuccess = onLoginSuccess
            )

            AppNavHost(
                appState = appState,
                isUserLoggedIn = isUserLoggedIn,
                openLoginBottomSheet = openLoginBottomSheet,
                onLogout = onLogout
            )
        }

    }
}