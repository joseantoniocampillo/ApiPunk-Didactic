@file:OptIn(ExperimentalCoroutinesApi::class)

package dev.campi.apipunksolution.usecases.suit

import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.coVerifyNever
import dev.campi.data.repository.Repository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class UpdateDatabaseTest {

    private lateinit var repository: Repository
    private lateinit var updateDatabase: UpdateDatabase

    private val oneHourInMillis = 3600_000L

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        updateDatabase = UpdateDatabase(repository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `WHEN invoke updateDatabase if was update 4 hours ago and cache policy is 5 THEN no save data`() = runTest {
        /* Given */
        val fourHours = 4
        val fiveHours = 5
        val calendar: Calendar = mockk(relaxed = true)
        mockkStatic(Calendar::class)
        every { Calendar.getInstance() } returns calendar
        every { calendar.timeInMillis } returns System.currentTimeMillis()
        every { repository.lastUpdateTime() } returns flow {
            emit(System.currentTimeMillis() - fourHours * oneHourInMillis)
        }
        val policy = fiveHours * oneHourInMillis

        /* When */
        updateDatabase.invoke(policy)

        /* Then */
        coVerifyNever { repository.getBeersPage(0, ITEM_PER_PAGE) }
        coVerifyNever { repository.addBeers(any()) }
    }

    @Test
    fun `WHEN invoke updateDatabase if was update 6 hours ago and cache policy is 5 THEN save data`() = runTest {
        /* Given */
        val sixHours = 6
        val fiveHours = 5
        val calendar: Calendar = mockk(relaxed = true)
        mockkStatic(Calendar::class)
        every { Calendar.getInstance() } returns calendar
        every { calendar.timeInMillis } returns System.currentTimeMillis()
        every { repository.lastUpdateTime() } returns flow {
            emit(System.currentTimeMillis() - sixHours * oneHourInMillis)
        }
        val policy = fiveHours * oneHourInMillis
        val beerType: BeerType = mockk(relaxed = true)
        val result: Result<List<BeerType>> = Result.success(listOf(beerType))
        coEvery { repository.getBeersPage(any(), ITEM_PER_PAGE) } returns result

        /* When */
        updateDatabase.invoke(policy)

        /* Then */
        coVerify { repository.getBeersPage(1, ITEM_PER_PAGE) }
        coVerify { repository.getBeersPage(2, ITEM_PER_PAGE) }
        coVerify { repository.getBeersPage(3, ITEM_PER_PAGE) }
        coVerify { repository.getBeersPage(4, ITEM_PER_PAGE) }
        coVerify { repository.getBeersPage(5, ITEM_PER_PAGE) }
        coVerify(exactly = 5) { repository.addBeers(listOf(beerType)) }
        coVerify(exactly = 1) { repository.saveUpdateTime(any()) }
    }
}