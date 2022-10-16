package dev.campi.apipunk.domain.model.beer_type


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hop(
    @SerialName("name")
    var name: String?,
    @SerialName("amount")
    var amount: Amount?,
    @SerialName("add")
    var add: String?,
    @SerialName("attribute")
    var attribute: String?
)