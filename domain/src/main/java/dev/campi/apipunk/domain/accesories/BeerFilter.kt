package dev.campi.apipunk.domain.accesories

sealed class BeerFilter(val name: String) {
    object PlusAlcohol : BeerFilter("+ Alcohol")
    object LessAlcohol : BeerFilter("- Alcohol")
    object PlusBitterness : BeerFilter("+ Bitterless")
    object BitternessLess : BeerFilter("- Bitterless")
}
