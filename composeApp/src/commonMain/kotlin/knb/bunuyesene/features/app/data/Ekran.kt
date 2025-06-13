package knb.bunuyesene.features.app.data

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import knb.bunuyesene.features.detail.navigation.TARIF_ID_ARG
import bunuyesene.composeapp.generated.resources.Res
import bunuyesene.composeapp.generated.resources.bookmark_selected
import bunuyesene.composeapp.generated.resources.bookmark_unselected
import bunuyesene.composeapp.generated.resources.detay
import bunuyesene.composeapp.generated.resources.favoriler
import bunuyesene.composeapp.generated.resources.anasayfa
import bunuyesene.composeapp.generated.resources.home_selected
import bunuyesene.composeapp.generated.resources.home_unselected
import bunuyesene.composeapp.generated.resources.profil
import bunuyesene.composeapp.generated.resources.profile_selected
import bunuyesene.composeapp.generated.resources.profile_unselected
import bunuyesene.composeapp.generated.resources.arama
import bunuyesene.composeapp.generated.resources.tabs

sealed class Ekran(
    val route: String,
    val resourceId: StringResource,
    val selectedIcon: DrawableResource? = null,
    val unselectedIcon: DrawableResource? = null,
) {

    data object Arama : Ekran("arama", Res.string.arama)
    data object Tabs : Ekran("tabs", Res.string.tabs)
    data object Detay : Ekran("detay?$TARIF_ID_ARG={$TARIF_ID_ARG}", Res.string.detay)

    data object Anasayfa : Ekran("home",
        Res.string.anasayfa,
        selectedIcon = Res.drawable.home_selected,
        unselectedIcon = Res.drawable.home_unselected
    )

    data object Favori : Ekran("favorites",
        Res.string.favoriler,
        selectedIcon = Res.drawable.bookmark_selected,
        unselectedIcon = Res.drawable.bookmark_unselected
    )

    data object Profil : Ekran("profile",
        Res.string.profil,
        selectedIcon = Res.drawable.profile_selected,
        unselectedIcon = Res.drawable.profile_unselected
    )
}