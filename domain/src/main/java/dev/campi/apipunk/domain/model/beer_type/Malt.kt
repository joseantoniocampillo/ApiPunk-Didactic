package dev.campi.apipunk.domain.model.beer_type


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Malt(
    @SerialName("name")
    var name: String?,
    @SerialName("amount")
    var amount: Amount?
)