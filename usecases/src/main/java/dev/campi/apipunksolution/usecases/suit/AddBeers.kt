package dev.campi.apipunksolution.usecases.suit

import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.data.repository.Repository
import javax.inject.Inject


class AddBeers @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(beers: List<BeerType>) = repository.addBeers(beers)
}
