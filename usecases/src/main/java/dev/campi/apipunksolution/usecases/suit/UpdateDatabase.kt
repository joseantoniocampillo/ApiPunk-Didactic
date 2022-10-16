package dev.campi.apipunksolution.usecases.suit

import dev.campi.apipunksolution.usecases.toListBeerType
import dev.campi.data.repository.Repository
import kotlinx.coroutines.flow.first
import java.util.*

/**
 * Api punk has 325 items, and offers max 80 items per page.
 * On this way 5 iterations are enough to go around
 */

const val LAST_PAGE_66_PER_PAGE = 5
const val ITEM_PER_PAGE = 66 // * 5 iterations go around all items api

class UpdateDatabase(private val repository: Repository) {

    private val now by lazy { Calendar.getInstance().timeInMillis }

    suspend operator fun invoke(cachePolicy: Long) {
        val lastUpdate = repository.lastUpdateTime().first()
        if (now - cachePolicy > lastUpdate)
            (1..LAST_PAGE_66_PER_PAGE).forEach { page ->
                val response = repository.getBeersPage(page, ITEM_PER_PAGE)
                if (response.isSuccess) {
                    repository.addBeers(response.toListBeerType())
                    if (page == 5)
                        repository.saveUpdateTime(now)
                }
            }
    }
}