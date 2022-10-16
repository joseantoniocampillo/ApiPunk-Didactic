package dev.campi.apipunksolution.data.local

import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.data.local.dao.BeerDao
import dev.campi.apipunksolution.data.model.mappers.BeerMappers.toBeerType
import dev.campi.apipunksolution.data.model.mappers.BeerMappers.toListBeerDataModel
import dev.campi.apipunksolution.data.model.mappers.BeerMappers.toListBeerType
import dev.campi.data.local.LocalDatasource
import javax.inject.Inject

class LocalDatasourceImpl @Inject constructor(
    private val dao: BeerDao
): LocalDatasource {
    override suspend fun getAllBeers(): List<BeerType>? {
        return dao.getAllBeers()?.toListBeerType()
    }

    override suspend fun getSelectedBeer(beerId: Int): BeerType? {
        return dao.getSelectedBeer(beerId)?.toBeerType()
    }

    override suspend fun addBeers(beers: List<BeerType>) {
        dao.addBeers(beers.toListBeerDataModel())
    }

    override suspend fun getAllBeersContain(query: String): List<BeerType>? {
        return dao.getAllBeersContain(query)?.toListBeerType()
    }
}
