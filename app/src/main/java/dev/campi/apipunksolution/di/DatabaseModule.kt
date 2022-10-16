package dev.campi.apipunksolution.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.campi.apipunksolution.data.local.BeerDatabase
import dev.campi.apipunksolution.data.local.dao.BeerDao
import dev.campi.apipunksolution.util.Constants.BEER_DATABASE
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): BeerDatabase = Room.databaseBuilder(
        context,
        BeerDatabase::class.java,
        BEER_DATABASE
    ).build()

    @Provides
    @Singleton
    fun provideDao(
        beerDatabase: BeerDatabase
    ): BeerDao {
        return beerDatabase.beerDao()
    }
}