package dev.campi.apipunksolution.data.server

import android.accounts.NetworkErrorException
import android.util.Log
import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.data.remote.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: PunkApiService
) : RemoteDataSource {

    override suspend fun getBeersPage(page: Int, perPage: Int): Result<List<BeerType>> =
        apiService.getBeersPage(page, perPage).toResultListBeerType()

    override suspend fun getBeersWithCharsInNamePagined(page: Int, perPage: Int, name: String): Result<List<BeerType>> =
        apiService.getBeersWithCharsInNamePagined(page, perPage, name).toResultListBeerType()

    private fun Response<List<BeerType>>.toResultListBeerType(): Result<List<BeerType>> {
        return runCatching {
            val response = this
            response.body()?.let {
                Log.d(TAG, "x-ratelimit-remaining: ${response.headers()
                    .find { header -> header.first == "x-ratelimit-remaining" }?.second}")
                Result.success(it)
            } ?: Result.failure(NetworkErrorException("code:${response.code()}"))
        }.getOrElse {
            Result.failure(it)
        }
    }
}

const val TAG = "qqqqq"
