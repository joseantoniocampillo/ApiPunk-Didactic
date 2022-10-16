package dev.campi.data.repository

import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.data.local.LocalDatasource
import dev.campi.data.local.PreferencesDatasource
import dev.campi.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDatasource: LocalDatasource,
    private val preferencesDatasource: PreferencesDatasource
) {

    suspend fun getBeersPage(
        page: Int = 1,
        perPage: Int = 40
    ): Result<List<BeerType>> = remoteDataSource.getBeersPage(page, perPage)

    suspend fun getAllBeers(): List<BeerType>? = localDatasource.getAllBeers()

    suspend fun getSelectedBeer(beerId: Int): BeerType? = localDatasource.getSelectedBeer(beerId)

    suspend fun addBeers(beers: List<BeerType>) = localDatasource.addBeers(beers)

    suspend fun getAllBeersContain(query: String): List<BeerType>? = localDatasource.getAllBeersContain(query)

    fun lastUpdateTime(): Flow<Long> = preferencesDatasource.getLastUpdatedTime()

    suspend fun saveUpdateTime(time: Long) {
        preferencesDatasource.setLastUpdatedTime(time)
    }
}
