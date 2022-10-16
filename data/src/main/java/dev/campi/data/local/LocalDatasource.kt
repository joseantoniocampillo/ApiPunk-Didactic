package dev.campi.data.local

import dev.campi.apipunk.domain.model.beer_type.BeerType

interface LocalDatasource {

    suspend fun getAllBeers(): List<BeerType>?

    suspend fun getSelectedBeer(beerId: Int): BeerType?

    suspend fun addBeers(beers: List<BeerType>)

    suspend fun getAllBeersContain(query: String): List<BeerType>?
}