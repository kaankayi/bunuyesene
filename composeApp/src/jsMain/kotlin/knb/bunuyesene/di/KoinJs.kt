package knb.bunuyesene.di

import knb.bunuyesene.dbFactory.DatabaseFactory
import org.koin.dsl.module
import knb.bunuyesene.preferences.MultiplatformSettingsFactory

val jsModules = module {
    single { DatabaseFactory()}
    single { MultiplatformSettingsFactory() }
}

fun initKoinJs() = initKoin(additionalModules = listOf(jsModules))