package dev.campi.apipunksolution.util

import dev.campi.apipunk.domain.model.beer_type.Hop
import dev.campi.apipunk.domain.model.beer_type.Malt

val Double?.intString: String get() = this?.toInt()?.toString().orEmpty()
val Double?.orNotData: String get() = this?.toString()?: "(no data)"

fun Malt.toViewIngredients(): String = "${this.name}. ${this.amount?.value.orNotData} ${this.amount?.unit.orEmpty()}"
fun Hop.toViewIngredients(): String {
    val attribute = if (this.attribute == null) "" else " (${this.attribute})"
    val add = if (this.add == null) "" else ". Added at ${this.add}"
    return "${this.name}. ${this.amount?.value.orNotData} ${this.amount?.unit.orEmpty()}$add$attribute"
}