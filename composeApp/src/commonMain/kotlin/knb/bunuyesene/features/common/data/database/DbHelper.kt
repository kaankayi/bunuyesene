package knb.bunuyesene.features.common.data.database

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import knb.bunuyesene.BunuYeseneDb
import knb.bunuyesene.dbFactory.DatabaseFactory
import knbbunuyesene.Tarif

class DbHelper(
    private val driverFactory: DatabaseFactory
) {

    private var db: BunuYeseneDb? = null
    private val mutex = Mutex()

    suspend fun <Result : Any?> withDatabase(block: suspend (BunuYeseneDb) -> Result) =
        mutex.withLock {
            if (db == null) {
                db = createDb(driverFactory)
            }

            return@withLock block(db!!)
        }

    private suspend fun createDb(driverFactory: DatabaseFactory): BunuYeseneDb {
        return BunuYeseneDb(
            driver = driverFactory.createDriver(),
            TarifAdapter = Tarif.Adapter(
                ingredientsAdapter = listOfStringsAdapter,
                instructionsAdapter = listOfStringsAdapter

            )
        )
    }
}
