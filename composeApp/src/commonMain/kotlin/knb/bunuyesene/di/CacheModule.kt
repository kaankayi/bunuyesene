package knb.bunuyesene.di
import org.koin.dsl.module
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext
import knb.bunuyesene.features.common.data.database.DbHelper
import knb.bunuyesene.features.common.data.database.daos.TarifDao
import knb.bunuyesene.features.common.data.database.daos.FavoriTarifDao


fun cacheModule()  = module {

    single<CoroutineContext> { Dispatchers.Default }
    single { CoroutineScope(get()) }

    single { DbHelper(get()) }
    single { TarifDao(get()) }
    single { FavoriTarifDao(get()) }
}