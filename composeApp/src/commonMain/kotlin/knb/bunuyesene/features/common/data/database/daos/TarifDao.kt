package knb.bunuyesene.features.common.data.database.daos

import knb.bunuyesene.features.common.data.database.DbHelper
import knb.bunuyesene.features.common.domain.entities.TarifItem
import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import knb.bunuyesene.features.common.data.database.tarifEntityMapper



class TarifDao(
    private val dbHelper: DbHelper
) {

    suspend fun insertTarif(tarifItem: TarifItem) {
        dbHelper.withDatabase { database ->
            database.tarifEntityQueries.insertTarif(
               tarifItem.id,
                tarifItem.title,
                tarifItem.description,
                tarifItem.category,
                tarifItem.area,
                tarifItem.imageUrl,
                tarifItem.youtubeLink,
                tarifItem.ingredients,
                tarifItem.instructions,
                if (tarifItem.isFavori) 1 else 0,
                tarifItem.rating,
                tarifItem.duration,
                tarifItem.difficulty
            )
        }
    }

    suspend fun updateTarif(tarifItem: TarifItem) {
        dbHelper.withDatabase { database ->
            database.tarifEntityQueries.updateTarif(
                tarifItem.title,
                tarifItem.description,
                tarifItem.category,
                tarifItem.area,
                tarifItem.imageUrl,
                tarifItem.youtubeLink,
                tarifItem.ingredients,
                tarifItem.instructions,
                if (tarifItem.isFavori) 1 else 0,
                tarifItem.rating,
                tarifItem.duration,
                tarifItem.difficulty,
                tarifItem.id
            )
        }
    }

    suspend fun insertTarifBulk(recipes: List<TarifItem>) {
        dbHelper.withDatabase { database ->
             recipes.forEach { tarifItem ->
                database.tarifEntityQueries.insertTarif(
                    tarifItem.id,
                    tarifItem.title,
                    tarifItem.description,
                    tarifItem.category,
                    tarifItem.area,
                    tarifItem.imageUrl,
                    tarifItem.youtubeLink,
                    tarifItem.ingredients,
                    tarifItem.instructions,
                    if (tarifItem.isFavori) 1 else 0,
                    tarifItem.rating,
                    tarifItem.duration,
                    tarifItem.difficulty
                )
            }
        }
    }

    suspend fun upsertTarifBulk(recipes: List<TarifItem>) {
        dbHelper.withDatabase { database ->
            recipes.forEach { tarifItem ->
                database.tarifEntityQueries.upsertTarif(
                    tarifItem.title,
                    tarifItem.description,
                    tarifItem.category,
                    tarifItem.area,
                    tarifItem.imageUrl,
                    tarifItem.youtubeLink,
                    tarifItem.ingredients,
                    tarifItem.instructions,
                    if (tarifItem.isFavori) 1 else 0,
                    tarifItem.rating,
                    tarifItem.duration,
                    tarifItem.difficulty,
                    tarifItem.id,
                )
            }
        }
    }

    suspend fun getAllTarifler(): List<TarifItem> {

        return dbHelper.withDatabase { database ->
            database.tarifEntityQueries.selectAllTarifler().awaitAsList().map {
                tarifEntityMapper(it)
            }
        }

    }

    suspend fun getTarifById(id: Long): TarifItem? {

        return dbHelper.withDatabase { database ->
            database.tarifEntityQueries.selectTarifById(id).awaitAsOneOrNull()?.let {
                tarifEntityMapper(it)
            }
        }

    }

    suspend fun deleteTarifById(id: Long) {
        dbHelper.withDatabase { database ->
            database.tarifEntityQueries.deleteTarifById(id)
        }
    }


    suspend fun araTarifByText(text: String): List<TarifItem> {
        return dbHelper.withDatabase { database ->
           database.tarifEntityQueries.araTarifByText(text).awaitAsList().map {
                tarifEntityMapper(it)
            }
        }
    }

}