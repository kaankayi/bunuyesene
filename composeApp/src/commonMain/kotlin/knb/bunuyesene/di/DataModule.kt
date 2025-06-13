package knb.bunuyesene.di
import org.koin.dsl.module
import knb.bunuyesene.features.detail.data.datasources.TarifDetayLocalDataSource
import knb.bunuyesene.features.detail.data.datasources.TarifDetayLocalDataSourceImpl
import knb.bunuyesene.features.detail.data.datasources.TarifDetayRemoteDataSource
import knb.bunuyesene.features.detail.data.datasources.TarifDetayRemoteDataSourceImpl
import knb.bunuyesene.features.detail.data.repositories.TarifDetayRepositoryImpl
import knb.bunuyesene.features.detail.repositories.TarifDetayRepository
import knb.bunuyesene.features.favorites.data.FavoriTarifLocalDataSource
import knb.bunuyesene.features.favorites.data.FavoriTarifLocalDataSourceImpl
import knb.bunuyesene.features.favorites.data.FavoriTarifRepositoryImpl
import knb.bunuyesene.features.favorites.domain.FavoriTarifRepository
import knb.bunuyesene.features.feed.data.datasources.FeedLocalDataSource
import knb.bunuyesene.features.feed.data.datasources.FeedLocalDataSourceImpl
import knb.bunuyesene.features.feed.data.datasources.FeedRemoteDataSource
import knb.bunuyesene.features.feed.data.datasources.FeedRemoteDataSourceImpl
import knb.bunuyesene.features.feed.data.repositories.FeedRepositoryImpl
import knb.bunuyesene.features.feed.domain.repositories.FeedRepository
import knb.bunuyesene.features.search.data.datasources.TarifAraLocalDataSource
import knb.bunuyesene.features.search.data.datasources.TarifAraLocalDataSourceImpl
import knb.bunuyesene.features.search.data.repositories.TarifAraRepositoryImpl
import knb.bunuyesene.features.search.domain.repositories.TarifAraRepository
import knb.bunuyesene.preferences.AppPreferences
import knb.bunuyesene.preferences.AppPreferencesImpl

fun dataModule()  = module {

    single<AppPreferences> { AppPreferencesImpl(get()) }

    single<FeedLocalDataSource> { FeedLocalDataSourceImpl(get()) }
    single<FeedRemoteDataSource> { FeedRemoteDataSourceImpl(get()) }

    single<TarifDetayLocalDataSource> { TarifDetayLocalDataSourceImpl(get(), get()) }
    single<TarifDetayRemoteDataSource> { TarifDetayRemoteDataSourceImpl(get()) }

    single<FavoriTarifLocalDataSource> { FavoriTarifLocalDataSourceImpl(get()) }

    single<FeedRepository> { FeedRepositoryImpl(get(), get()) }
    single<TarifDetayRepository> { TarifDetayRepositoryImpl(get(), get()) }
    single<FavoriTarifRepository> { FavoriTarifRepositoryImpl(get()) }

    single<TarifAraLocalDataSource> { TarifAraLocalDataSourceImpl(get()) }
    single<TarifAraRepository> { TarifAraRepositoryImpl(get()) }
}