package knb.bunuyesene.dbFactory

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import knb.bunuyesene.BunuYeseneDb
import java.util.Properties

actual class DatabaseFactory {
    actual suspend fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver(
            JdbcSqliteDriver.IN_MEMORY,
            properties = Properties().apply { put("foreign_keys", "true") }
        )
        BunuYeseneDb.Schema.awaitCreate(driver)
        return driver
    }
}