package dev.campi.apipunk.domain.model.beer_type


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MashTemp(
    @SerialName("temp")
    var temp: Temp?,
    @SerialName("duration")
    var duration: Double?
)
