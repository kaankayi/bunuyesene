package knb.bunuyesene

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import knb.bunuyesene.di.initKoin
import knb.bunuyesene.dbFactory.DatabaseFactory

class MainApplication: Application() {
    private val androidModules = module {
single {DatabaseFactory(applicationContext) }
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

