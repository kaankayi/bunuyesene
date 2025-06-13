package knb.bunuyesene.features.common.data.database.daos

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import knb.bunuyesene.features.common.data.database.DbHelper
import knb.bunuyesene.features.common.data.database.tarifEntityMapper
import knb.bunuyesene.features.common.domain.entities.TarifItem

class FavoriTarifDao(
    private val dbHelper: DbHelper
) {

    suspend fun addFavori(tarifId: Long) {
        val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        dbHelper.withDatabase { database ->
            database.favoriTarifQueries.upsertFavori(
                tarif_id = tarifId, added_at = currentDateTime.toString()
            )
        }
    }

    suspend fun removeFavori(tarifId: Long) {
        dbHelper.withDatabase { database ->
            database.favoriTarifQueries.deleteFavoriByTarifId(
                tarif_id = tarifId
            )
        }
    }

    suspend fun getAllFavoriTarifler(): List<TarifItem> {
        return dbHelper.withDatabase { database ->
            database.favoriTarifQueries.selectAllFavoriTarifler().awaitAsList().map {
                tarifEntityMapper(it)
            }
        }
    }

    suspend fun isFavori(tarifId: Long): Boolean {
        return dbHelper.withDatabase { database ->
            database.favoriTarifQueries.selectFavoriByTarifId(tarifId).awaitAsOneOrNull() != null
        }
    }


}