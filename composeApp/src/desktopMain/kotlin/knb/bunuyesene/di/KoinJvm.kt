package knb.bunuyesene.di

import knb.bunuyesene.dbFactory.DatabaseFactory
import org.koin.dsl.module

val jvmModules = module {
    single {
        DatabaseFactory()
    }
}
fun initKoinJvm() = initKoin(additionalModules = listOf(jvmModules))