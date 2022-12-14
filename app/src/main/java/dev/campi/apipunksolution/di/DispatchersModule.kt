package dev.campi.apipunksolution.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.campi.data.dispatchers.DispatchersProvides
import dev.campi.data.dispatchers.ProdDispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Singleton
    fun provideDispatchers(): DispatchersProvides = ProdDispatchers()
}