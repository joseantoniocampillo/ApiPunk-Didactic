package dev.campi.apipunksolution.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.campi.apipunksolution.presentation.list.BeerAdapter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

    @Provides
    @Singleton
    fun provideAdapter(
    ): BeerAdapter = BeerAdapter()
}