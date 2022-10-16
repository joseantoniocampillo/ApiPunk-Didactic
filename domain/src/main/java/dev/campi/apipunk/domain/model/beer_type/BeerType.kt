package dev.campi.apipunk.domain.model.beer_type


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeerType(
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
    var volume: Volume?,
    @SerialName("boil_volume")
    var boilVolume: BoilVolume?,
    @SerialName("method")
    var method: Method?,
    @SerialName("ingredients")
    var ingredients: Ingredients?,
    @SerialName("food_pairing")
    var foodPairing: List<String>?,
    @SerialName("brewers_tips")
    var brewersTips: String?,
    @SerialName("contributed_by")
    var contributedBy: String?
)
