package dev.campi.apipunk.domain.model.beer_type


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Method(
    @SerialName("mash_temp")
    var mashTemp: List<MashTemp>?,
    @SerialName("fermentation")
    var fermentation: Fermentation?,
    @SerialName("twist")
    var twist: String?
)