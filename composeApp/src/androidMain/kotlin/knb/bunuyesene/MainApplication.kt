package knb.bunuyesene

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import knb.bunuyesene.di.initKoin

class MainApplication: Application() {
    private val androidModules = module {

    }
    override fun onCreate() {
        super.onCreate()
    }
    private fun setupKoin() {
        initKoin(additionalModules = listOf(androidModules)) {
            androidContext(applicationContext)
        }
    }
}

