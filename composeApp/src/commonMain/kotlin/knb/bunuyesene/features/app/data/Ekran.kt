package knb.bunuyesene.features.app.data

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import knb.bunuyesene.features.detail.navigation.TARIF_ID_ARG
import bunuyesene.composeapp.generated.resources.Res
import bunuyesene.composeapp.generated.resources.bookmark_selected
import bunuyesene.composeapp.generated.resources.bookmark_unselected
import bunuyesene.composeapp.generated.resources.detail
import bunuyesene.composeapp.generated.resources.favorites
import bunuyesene.composeapp.generated.resources.home
import bunuyesene.composeapp.generated.resources.home_selected
import bunuyesene.composeapp.generated.resources.home_unselected
import bunuyesene.composeapp.generated.resources.profile
import bunuyesene.composeapp.generated.resources.profile_selected
import bunuyesene.composeapp.generated.resources.profile_unselected
import bunuyesene.composeapp.generated.resources.search
import bunuyesene.composeapp.generated.resources.tabs

sealed class Ekran(
    val route: String,
    val resourceId: StringResource,
    val selectedIcon: DrawableResource? = null,
    val unselectedIcon: DrawableResource? = null,
) {

    data object Arama : Ekran("arama", Res.string.search)
    data object Tabs : Ekran("tabs", Res.string.tabs)
    data object Detay : Ekran("detay?$TARIF_ID_ARG={$TARIF_ID_ARG}", Res.string.detail)

    data object Anasayfa : Ekran("home",
        Res.string.home,
        selectedIcon = Res.drawable.home_selected,
        unselectedIcon = Res.drawable.home_unselected
    )

    data object Favori : Ekran("favorites",
        Res.string.favorites,
        selectedIcon = Res.drawable.bookmark_selected,
        unselectedIcon = Res.drawable.bookmark_unselected
    )

    data object Profil : Ekran("profile",
        Res.string.profile,
        selectedIcon = Res.drawable.profile_selected,
        unselectedIcon = Res.drawable.profile_unselected
    )
}