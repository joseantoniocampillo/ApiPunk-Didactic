package dev.campi.apipunksolution.usecases.suit

import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.data.repository.Repository


class GetAllBeersContain(private val repository: Repository) {

    private fun String.queryParsed() = "%$this%"

    suspend operator fun invoke(query: String): List<BeerType>? = repository.getAllBeersContain(query.queryParsed())
}