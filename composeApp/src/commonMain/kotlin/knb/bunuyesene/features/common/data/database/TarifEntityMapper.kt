package knb.bunuyesene.features.common.data.database

import knb.bunuyesene.features.common.domain.entities.TarifItem
import knbbunuyesene.Tarif


fun tarifEntityMapper(tarif: Tarif)  = TarifItem(
    tarif.id,
    tarif.title,
    tarif.description,
    tarif.category,
    tarif.area,
    tarif.imageUrl,
    tarif.youtubeLink,
    tarif.ingredients,
    tarif.instructions,
    tarif.isFavori == 1L,
    tarif.rating,
    tarif.duration ?: "25 Dakika",
    tarif.difficulty ?: "Kolay"
)
