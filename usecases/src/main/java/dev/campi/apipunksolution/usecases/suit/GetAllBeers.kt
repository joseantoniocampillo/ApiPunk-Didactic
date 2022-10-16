package dev.campi.apipunksolution.usecases.suit

import dev.campi.data.repository.Repository
import javax.inject.Inject

class GetAllBeers @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.getAllBeers()
}