package dev.campi.data.remote

import dev.campi.apipunk.domain.model.beer_type.BeerType

interface RemoteDataSource {

    suspend fun getBeersPage(
        page: Int = 1,
        perPage: Int = 40
    ): Result<List<BeerType>>

    suspend fun getBeersWithCharsInNamePagined(page: Int, perPage: Int, name: String): Result<List<BeerType>>
}