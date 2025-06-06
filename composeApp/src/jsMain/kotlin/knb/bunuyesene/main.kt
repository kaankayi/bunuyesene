package knb.bunuyesene

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import knb.bunuyesene.di.initKoinJs
import org.jetbrains.compose.web.renderComposable

val koin = initKoinJs()


fun main() {
    renderComposable(rootElementId = "root")

    {
        App()
    }
}