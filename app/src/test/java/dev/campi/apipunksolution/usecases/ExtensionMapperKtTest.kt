package dev.campi.apipunksolution.usecases

import dev.campi.apipunk.domain.accesories.BeerFilter
import dev.campi.apipunk.domain.model.beer_type.BeerType
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionMapperKtTest {

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `WHEN sorted by alcohol THEN exclude beers when its abv field is null`() {
        /* Given */
        val beerType: BeerType = mockk {
            every { abv } returns null
        }
        val list = listOf(
            beerType, beerType
        )

        /* When */
        val sorted = list.sortedByBeerFilter(BeerFilter.PlusAlcohol)

        /* Then */
        assert(sorted.isEmpty())
    }

    @Test
    fun `WHEN sorted by alcohol THEN assert than expected`() {
        /* Given */
        val beerType: BeerType = mockk(relaxed = true) {
            every { id } returns 1
            every { abv } returns 2.0
        }
        val beerType2: BeerType = mockk(relaxed = true) {
            every { id } returns 2
            every { abv } returns 3.0
        }

        val list = listOf(
            beerType, beerType2
        )

        /* When */
        val sorted = list.sortedByBeerFilter(BeerFilter.PlusAlcohol)

        /* Then */
        assert(sorted.isNotEmpty())
        assertEquals(3.0, sorted.first().abv)
    }

    @Test
    fun `WHEN sorted by bitterness THEN filter only not null for ibu field`() {
        /* Given */
        val beerType: BeerType = mockk {
            every { ibu } returns null
        }
        val list = listOf(
            beerType, beerType
        )

        /* When */
        val sorted = list.sortedByBeerFilter(BeerFilter.BitternessLess)

        /* Then */
        assert(sorted.isEmpty())
    }

    @Test
    fun `WHEN sorted by bitterness THEN assert than expected`() {
        /* Given */
        val beerType: BeerType = mockk(relaxed = true) {
            every { id } returns 1
            every { ibu } returns 2.0
        }
        val beerType2: BeerType = mockk(relaxed = true) {
            every { id } returns 2
            every { ibu } returns 3.0
        }

        val list = listOf(
            beerType, beerType2
        )

        /* When */
        val sorted = list.sortedByBeerFilter(BeerFilter.BitternessLess)

        /* Then */
        assert(sorted.isNotEmpty())
        assertEquals(2.0, sorted.first().ibu)
    }
}