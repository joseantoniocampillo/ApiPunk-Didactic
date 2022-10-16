package dev.campi.apipunk.domain.model.beer_type


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ingredients(
    @SerialName("malt")
    var malt: List<Malt>?,
    @SerialName("hops")
    var hops: List<Hop>?,
    @SerialName("yeast")
    var yeast: String?
)