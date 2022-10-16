package dev.campi.apipunksolution.usecases.suit

import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.usecases.toListBeerType
import dev.campi.data.repository.Repository
import javax.inject.Inject


class GetBeersFromRemotePaging @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(
        page: Int,
        perPage: Int
    ):List<BeerType> {
        return repository.getBeersPage(page, perPage).toListBeerType()
    }
}