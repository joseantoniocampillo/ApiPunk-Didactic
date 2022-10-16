package dev.campi.apipunksolution.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.campi.apipunk.domain.model.JsonAsStringSerializer
import dev.campi.apipunksolution.util.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.BEER_DATABASE_TABLE)
data class BeerEntityModel(
    @PrimaryKey(autoGenerate = false)
    @SerialName("id")
    var id: Int,

    @SerialName("name")
    var name: String?,

    @SerialName("tagline")
    var tagline: String?,

    @SerialName("first_brewed")
    var firstBrewed: String?,

    @SerialName("description")
    var description: String?,

    @SerialName("image_url")
    var imageUrl: String?,

    @SerialName("abv")
    var abv: Double?,

    @SerialName("ibu")
    var ibu: Double?,

    @SerialName("target_fg")
    var targetFg: Double?,

    @SerialName("target_og")
    var targetOg: Double?,

    @SerialName("ebc")
    var ebc: Double?,

    @SerialName("srm")
    var srm: Double?,

    @SerialName("ph")
    var ph: Double?,

    @SerialName("attenuation_level")
    var attenuationLevel: Double?,

    @SerialName("volume")
    @Serializable(with = JsonAsStringSerializer::class)
    var volume: String?,

    @SerialName("boil_volume")
    @Serializable(with = JsonAsStringSerializer::class)
    var boilVolume: String?,

    @SerialName("method")
    @Serializable(with = JsonAsStringSerializer::class)
    var method: String?,

    @SerialName("ingredients")
    @Serializable(with = JsonAsStringSerializer::class)
    var ingredients: String?,

    @SerialName("food_pairing")
    @Serializable(with = JsonAsStringSerializer::class)
    var foodPairing: String?,

    @SerialName("brewers_tips")
    var brewersTips: String?,

    @SerialName("contributed_by")
    var contributedBy: String?
)
