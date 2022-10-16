package dev.campi.apipunksolution.usecases

import dev.campi.apipunksolution.usecases.suit.*

data class UseCases(
    val getAllBeers: GetAllBeers,
    val getSelectedBeer: GetSelectedBeer,
    val addBeers: AddBeers,
    val getBeersFromRemotePaging: GetBeersFromRemotePaging,
    val updateDatabase: UpdateDatabase,
    val getAllBeersContain: GetAllBeersContain
)