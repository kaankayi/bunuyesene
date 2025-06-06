package knb.bunuyesene.di

import knb.bunuyesene.dbFactory.DatabaseFactory
import org.koin.dsl.module

val jsModules = module {
    single { DatabaseFactory()}
}

fun initKoinJs() = initKoin(additionalModules = listOf(jsModules))