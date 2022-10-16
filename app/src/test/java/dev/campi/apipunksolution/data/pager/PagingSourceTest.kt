package dev.campi.apipunksolution.data.pager

import androidx.paging.PagingSource.LoadParams.Prepend
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingSource.LoadResult.Page
import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.mockkRelaxed
import dev.campi.apipunksolution.usecases.UseCases
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PagingSourceTest {

    private lateinit var pagingSource: PagingSource
    private val useCases: UseCases = mockkRelaxed()

    @Before
    fun setUp() {
        pagingSource = PagingSource(useCases)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `WHEN pagingsource request first page THEN return as expected`() = runTest {
        /* Given */
        val list: List<BeerType> = (1..40).map { idValue ->
            mockkRelaxed { every { id } returns idValue }
        }
        coEvery {
            useCases.getBeersFromRemotePaging(
                1,
                NETWORK_PAGE_SIZE
            )
        } returns list

        /* When */
        val actual: LoadResult<Int, BeerType> = pagingSource.load(
            Prepend(
                key = 1,
                loadSize = NETWORK_PAGE_SIZE,
                placeholdersEnabled = false
            )
        )

        /* Then */
        val expected: Page<Int, BeerType> = Page(
            data = list,
            prevKey = null,
            nextKey = 2
        )

        assertEquals(expected, actual)
    }
}