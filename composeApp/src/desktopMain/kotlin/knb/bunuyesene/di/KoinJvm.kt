package knb.bunuyesene.di

import org.koin.dsl.module

val jvmModules = module {

}

fun initKoinJvm() = initKoin(additionalModules = listOf(jvmModules))