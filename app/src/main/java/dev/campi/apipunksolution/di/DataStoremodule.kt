package dev.campi.apipunksolution.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.campi.apipunksolution.data.datastore.PreferencesDatasourceImpl
import dev.campi.data.local.PreferencesDatasource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoremodule {

    @Provides
    @Singleton
    fun provideDatastore(
        @ApplicationContext context: Context
    ): PreferencesDatasource = PreferencesDatasourceImpl(context)
}