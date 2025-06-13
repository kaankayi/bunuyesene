package knb.bunuyesene.di

import org.koin.dsl.module
import knb.bunuyesene.dbFactory.DatabaseFactory
import knb.bunuyesene.preferences.MultiplatformSettingsFactory

val jvmModules = module {
    single { DatabaseFactory() }
    single { MultiplatformSettingsFactory() }
}

fun initKoinJvm() = initKoin(additionalModules = listOf(jvmModules))