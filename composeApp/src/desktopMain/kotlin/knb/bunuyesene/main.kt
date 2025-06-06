package knb.bunuyesene

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import knb.bunuyesene.di.initKoinJvm

val koin = initKoinJvm()

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "BunuYesene",
    ) {
        App()
    }
}