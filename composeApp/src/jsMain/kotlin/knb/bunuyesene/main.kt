package knb.bunuyesene

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.bindToNavigation
import androidx.navigation.compose.rememberNavController
import androidx.savedstate.read
import kotlinx.browser.document
import org.jetbrains.skiko.wasm.onWasmReady
import kotlinx.browser.window
import knb.bunuyesene.di.initKoinJs
import knb.bunuyesene.features.app.data.Ekran
import knb.bunuyesene.features.detail.navigation.TARIF_ID_ARG

val koin = initKoinJs()

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    onWasmReady {
        ComposeViewport(document.body!!) {

            val navController = rememberNavController()

            App(navController = navController)

            LaunchedEffect(Unit) {


                val initRoute = window.location.hash.substringAfter("#", "")
                when {
                    initRoute.startsWith("detay") -> {
                        val id = initRoute.substringAfter("detay/").toLong()
                        navController.navigate(
                            Ekran.Detay.route.replace(
                                "$TARIF_ID_ARG={$TARIF_ID_ARG}",
                                "$TARIF_ID_ARG=$id"
                            )
                        )
                    }

                    initRoute.startsWith("arama") -> {
                        navController.navigate(Ekran.Arama.route)
                    }
                }

                window.bindToNavigation(navController) { entry ->
                    val route = entry.destination.route.orEmpty()

                    when {
                        route.startsWith(Ekran.Tabs.route) -> {
                            "#"
                        }

                        route.startsWith(Ekran.Detay.route) -> {
                            val args = entry.arguments
                            val id = args?.read {
                                getLongOrNull(TARIF_ID_ARG)
                            }

                            //#detail/1
                            "#detay/${id}"
                        }

                        route.startsWith(Ekran.Arama.route) -> {
                            "#arama"
                        }

                        else -> ""
                    }
                }
            }
        }
    }
}