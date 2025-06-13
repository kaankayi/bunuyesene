package knb.bunuyesene.dbFactory

import app.cash.sqldelight.db.SqlDriver

const val DB_FILE_NAME = "bunuyesene.db"

expect class DatabaseFactory {
    suspend fun createDriver(): SqlDriver
}