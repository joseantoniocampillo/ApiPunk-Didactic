package dev.campi.apipunk.domain.model.beer_type


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Amount(
    @SerialName("value")
    var value: Double?,
    @SerialName("unit")
    var unit: String?
)