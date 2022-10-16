package dev.campi.apipunksolution.data.model.mappers

import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.data.model.BeerEntityModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object BeerMappers {
    fun BeerEntityModel.toBeerType(): BeerType {
        return BeerType(
            id = id,
            name = name,
            tagline = tagline,
            firstBrewed = firstBrewed,
            description = description,
            imageUrl = imageUrl,
            abv = abv,
            ibu = ibu,
            targetFg = targetFg,
            targetOg = targetOg,
            ebc = ebc,
            srm = srm,
            ph = ph,
            attenuationLevel = attenuationLevel,
            volume = volume.toObjectOrNull(),
            boilVolume = boilVolume.toObjectOrNull(),
            method = method.toObjectOrNull(),
            ingredients = ingredients.toObjectOrNull(),
            foodPairing = foodPairing.toObjectOrNull(),
            brewersTips = brewersTips,
            contributedBy = contributedBy
        )
    }

    fun List<BeerEntityModel>.toListBeerType(): List<BeerType> {
        return map { it.toBeerType() }
    }

    fun BeerType.toBeerDataModel(): BeerEntityModel {
        return BeerEntityModel(
            id = id,
            name = name,
            tagline = tagline,
            firstBrewed = firstBrewed,
            description = description,
            imageUrl = imageUrl,
            abv = abv,
            ibu = ibu,
            targetFg = targetFg,
            targetOg = targetOg,
            ebc = ebc,
            srm = srm,
            ph = ph,
            attenuationLevel = attenuationLevel,
            volume = Json.encodeToString(volume),
            boilVolume = Json.encodeToString(boilVolume),
            method = Json.encodeToString(method),
            ingredients = Json.encodeToString(ingredients),
            foodPairing = Json.encodeToString(foodPairing),
            brewersTips = Json.encodeToString(brewersTips),
            contributedBy = Json.encodeToString(contributedBy)
        )
    }

    fun List<BeerType>.toListBeerDataModel(): List<BeerEntityModel> {
        return map { it.toBeerDataModel() }
    }
}

inline fun <reified T: Any?> String?.toObjectOrNull(): T? = this?.let {
    runCatching { Json.decodeFromString<T>(it) }.getOrNull()
}
