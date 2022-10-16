package dev.campi.apipunksolution.data.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.usecases.UseCases
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

const val STARTING_PAGE_INDEX = 1
const val NETWORK_PAGE_SIZE = 24

class PagingSource @Inject constructor(
    private val useCases: UseCases
) : PagingSource<Int, BeerType>() {
    override fun getRefreshKey(state: PagingState<Int, BeerType>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeerType> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = useCases.getBeersFromRemotePaging(position, params.loadSize)
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
