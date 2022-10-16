package dev.campi.apipunksolution.usecases

import dev.campi.apipunk.domain.accesories.BeerFilter
import dev.campi.apipunk.domain.model.beer_type.BeerType

fun Result<List<BeerType>>.toListBeerType(): List<BeerType> {
    fold(onSuccess = { return it }, onFailure = { return emptyList() })
}

fun List<BeerType>.sortedByBeerFilter(beerFilter: BeerFilter) = filter {
    if (beerFilter == BeerFilter.LessAlcohol || beerFilter == BeerFilter.PlusAlcohol) it.abv != null else it.ibu != null
}.sortedBy { beer ->
    when (beerFilter) {
        BeerFilter.PlusAlcohol -> beer.abv?.let { it * -1 }
        BeerFilter.LessAlcohol -> beer.abv
        BeerFilter.PlusBitterness -> beer.ibu?.let { it * -1 }
        BeerFilter.BitternessLess -> beer.ibu
    }
}

val allBeerFilters = listOf(
    BeerFilter.PlusAlcohol,
    BeerFilter.LessAlcohol,
    BeerFilter.PlusBitterness,
    BeerFilter.BitternessLess
)
