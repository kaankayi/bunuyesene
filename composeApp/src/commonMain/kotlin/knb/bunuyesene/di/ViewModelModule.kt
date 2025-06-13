package knb.bunuyesene.di

import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel
import knb.bunuyesene.features.feed.ui.FeedViewModel
import knb.bunuyesene.features.detail.ui.TarifDetayViewModel
import knb.bunuyesene.features.favorites.ui.FavoriEkraniViewModel
import knb.bunuyesene.features.login.ui.GirisViewModel
import knb.bunuyesene.features.profile.ui.ProfilViewModel
import knb.bunuyesene.features.search.ui.AramaViewModel

fun viewModelModule()  = module {

    viewModel {
        FeedViewModel(get())
    }

    viewModel {
        TarifDetayViewModel(get())
    }

    viewModel {
        FavoriEkraniViewModel(get())
    }

    viewModel {
        ProfilViewModel()
    }

    viewModel {
        GirisViewModel()
    }
    viewModel {
        AramaViewModel(get())
    }

}