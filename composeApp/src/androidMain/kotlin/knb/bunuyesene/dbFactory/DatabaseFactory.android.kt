package knb.bunuyesene.dbFactory


import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import knb.bunuyesene.BunuYeseneDb
import androidx.sqlite.db.SupportSQLiteDatabase


actual class DatabaseFactory(
    private val context: Context
) {
    actual suspend fun createDriver(): SqlDriver {
        val schema = BunuYeseneDb.Schema.synchronous()
        return AndroidSqliteDriver(
            schema, context, DB_FILE_NAME,
            callback = object : AndroidSqliteDriver.Callback(schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
    }
}