package dev.campi.apipunksolution.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.campi.apipunksolution.data.local.dao.BeerDao
import dev.campi.apipunksolution.data.model.BeerEntityModel

@Database(entities = [BeerEntityModel::class], version = 1)
abstract class BeerDatabase: RoomDatabase() {

    abstract fun beerDao(): BeerDao
}
