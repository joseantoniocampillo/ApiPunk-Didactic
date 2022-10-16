package dev.campi.apipunksolution.data.server

import dev.campi.apipunk.domain.model.beer_type.BeerType
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkApiService {

    @GET("beers/")
    suspend fun getBeersPage(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int
    ): Response<List<BeerType>>

    @GET("beers/")
    suspend fun getBeersWithCharsInNamePagined(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int,
        @Query("beer_name") name: String
    ): Response<List<BeerType>>
}
