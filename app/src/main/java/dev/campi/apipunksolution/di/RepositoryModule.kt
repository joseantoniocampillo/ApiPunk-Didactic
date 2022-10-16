package dev.campi.apipunksolution.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.campi.apipunksolution.usecases.UseCases
import dev.campi.apipunksolution.usecases.suit.*
import dev.campi.data.repository.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(
        repository: Repository
    ): UseCases {
        return UseCases(
            getAllBeers = GetAllBeers(repository),
            getSelectedBeer = GetSelectedBeer(repository),
            addBeers = AddBeers(repository),
            getBeersFromRemotePaging = GetBeersFromRemotePaging(repository),
            updateDatabase = UpdateDatabase(repository),
            getAllBeersContain = GetAllBeersContain(repository)
        )
    }
}
