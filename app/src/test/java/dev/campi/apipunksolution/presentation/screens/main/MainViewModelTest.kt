package dev.campi.apipunksolution.presentation.screens.main

import androidx.paging.PagingData
import dev.campi.apipunk.domain.accesories.BeerFilter
import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.coVerifyOnce
import dev.campi.apipunksolution.mockkRelaxed
import dev.campi.apipunksolution.usecases.UseCases
import dev.campi.apipunksolution.util.Constants
import dev.campi.data.dispatchers.DispatchersProvides
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.every
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val useCases: UseCases = mockkRelaxed()
    private val dispatchers: DispatchersProvides = mockkRelaxed()
    private val pagingData: Flow<PagingData<BeerType>> = mockkRelaxed()
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        every { dispatchers.io } returns testDispatcher
        mainViewModel = MainViewModel(useCases, dispatchers, pagingData)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `WHEN init THEN updateDatabase`() = runTest {
        coJustRun { useCases.updateDatabase(Constants.MILLIS_PER_DAY) }

        /* Then */
        coVerifyOnce { useCases.updateDatabase(Constants.MILLIS_PER_DAY) }
    }

    @Test
    fun `WHEN searchBeer THEN call getAllBeersContain query and update localBeerList value`() = runTest {
        /* Given */
        val beerType1: BeerType = mockkRelaxed { every { id } returns 1 }
        val beerType2: BeerType = mockkRelaxed { every { id } returns 2 }
        coEvery { useCases.getAllBeersContain("query") } returns listOf(beerType1, beerType2)

        /* When */
        mainViewModel.searchBeer("query")

        /* Then */
        val actual = mainViewModel.localBeerList
        assertEquals(1, actual.value?.firstOrNull()?.id)
        assertEquals(2, actual.value?.getOrNull(1)?.id)
    }

    @Test
    fun `WHEN applyFilter BitternessLess THEN return as expected`() = runTest {
        /* Given */
        coEvery { useCases.getAllBeers() } returns listOf(
            mockkRelaxed {
                every { id } returns 1
                every { abv } returns 8.0
                every { ibu } returns 20.0
            },
            mockkRelaxed {
                every { id } returns 2
                every { abv } returns 10.0
                every { ibu } returns 30.0
            },
            mockkRelaxed {
                every { id } returns 3
                every { abv } returns 2.0
                every { ibu } returns 5.0
            }
        )

        /* When */
        mainViewModel.applyFilter(BeerFilter.BitternessLess)

        /* Then */
        val idActual: Int? = mainViewModel.localBeerList.value?.firstOrNull()?.id
        assertEquals(3, idActual)
    }

    @Test
    fun `WHEN applyFilter PlusAlcohol THEN return as expected`() = runTest {
        /* Given */
        coEvery { useCases.getAllBeers() } returns listOf(
            mockkRelaxed {
                every { id } returns 1
                every { abv } returns 8.0
                every { ibu } returns 20.0
            },
            mockkRelaxed {
                every { id } returns 2
                every { abv } returns 10.0
                every { ibu } returns 30.0
            },
            mockkRelaxed {
                every { id } returns 3
                every { abv } returns 2.0
                every { ibu } returns 5.0
            }
        )

        /* When */
        mainViewModel.applyFilter(BeerFilter.PlusAlcohol)

        /* Then */
        val idActual: Int? = mainViewModel.localBeerList.value?.firstOrNull()?.id
        assertEquals(2, idActual)
    }

    @Test
    fun `WHEN applyFilter LessAlcohol THEN return as expected`() = runTest {
        /* Given */
        coEvery { useCases.getAllBeers() } returns listOf(
            mockkRelaxed {
                every { id } returns 1
                every { abv } returns 8.0
                every { ibu } returns 20.0
            },
            mockkRelaxed {
                every { id } returns 2
                every { abv } returns 10.0
                every { ibu } returns 30.0
            },
            mockkRelaxed {
                every { id } returns 3
                every { abv } returns 2.0
                every { ibu } returns 5.0
            }
        )

        /* When */
        mainViewModel.applyFilter(BeerFilter.LessAlcohol)

        /* Then */
        val idActual: Int? = mainViewModel.localBeerList.value?.firstOrNull()?.id
        assertEquals(3, idActual)
    }

    @Test
    fun `WHEN updateSearchQuery THEN set value for searhQuery mutableState field propertly`() = runTest {
        /* Given */
        val query = "nueva query"

        /* When */
        mainViewModel.updateSearchQuery(query)
        /* Then */

        val actual = mainViewModel.searchQuery.value
        assertEquals(query, actual)
    }
    
    @Test
    fun `WHEN viewmodel is instantiated THEN beerSelected state is null`() {
        /* Then */
        assertEquals(null, mainViewModel.beerSelected.value)
    }

    @Test
    fun `WHEN onBeerSelectedChange set with a value THEN beerSelected get this state`() {
        /* Given */
        val beerType: BeerType = mockkRelaxed { every { id } returns 27 }

        /* When */
        mainViewModel.onBeerSelectedChange(beerType)

        /* Then */
        val actual = mainViewModel.beerSelected.value?.id
        val expected = 27
        assertEquals(expected, actual)
    }
    @Test
    fun `WHEN onBeerSelectedChange set null THEN beerSelected state pass to null`() {
        /* When */
        mainViewModel.onBeerSelectedChange(null)

        /* Then */
        assertEquals(null, mainViewModel.beerSelected.value)
    }

}
