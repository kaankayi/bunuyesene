package knb.bunuyesene.features.common.data.database.daos

import knb.bunuyesene.features.common.data.database.DbHelper
import knb.bunuyesene.features.common.domain.entities.TarifItem
import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import knb.bunuyesene.features.common.data.database.TarifEntityMapper



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
                if (tarifItem.isFavorite) 1 else 0,
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
                if (tarifItem.isFavorite) 1 else 0,
                tarifItem.rating,
                tarifItem.duration,
                tarifItem.difficulty,
                tarifItem.id
            )
        }
    }

    suspend fun insertRecipesBulk(recipes: List<TarifItem>) {
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
                    if (tarifItem.isFavorite) 1 else 0,
                    tarifItem.rating,
                    tarifItem.duration,
                    tarifItem.difficulty
                )
            }
        }
    }

    suspend fun upsertRecipesBulk(recipes: List<TarifItem>) {
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
                    if (tarifItem.isFavorite) 1 else 0,
                    tarifItem.rating,
                    tarifItem.duration,
                    tarifItem.difficulty,
                    tarifItem.id,
                )
            }
        }
    }

    suspend fun getTumTarifler(): List<TarifItem> {

        return dbHelper.withDatabase { database ->
            database.tarifEntityQueries.selectTumTarifler().awaitAsList().map {
                TarifEntityMapper(it)
            }
        }

    }

    suspend fun getTarifById(id: Long): TarifItem? {

        return dbHelper.withDatabase { database ->
            database.tarifEntityQueries.selectTarifById(id).awaitAsOneOrNull()?.let {
                TarifEntityMapper(it)
            }
        }

    }

    suspend fun tarifSilById(id: Long) {
        dbHelper.withDatabase { database ->
            database.tarifEntityQueries.TarifSilById(id)
        }
    }


    suspend fun araTarifByText(text: String): List<TarifItem> {
        return dbHelper.withDatabase { database ->
           database.tarifEntityQueries.araTarifByText(text).awaitAsList().map {
                TarifEntityMapper(it)
            }
        }
    }

}