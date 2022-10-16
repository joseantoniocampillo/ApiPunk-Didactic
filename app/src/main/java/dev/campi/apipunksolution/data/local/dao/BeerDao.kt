package dev.campi.apipunksolution.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.campi.apipunksolution.data.model.BeerEntityModel

@Dao
interface BeerDao {

    @Query("SELECT * FROM beer_table ORDER BY id ASC")
    suspend fun getAllBeers(): List<BeerEntityModel>?

    @Query("SELECT * FROM beer_table WHERE id=:beerId")
    suspend fun getSelectedBeer(beerId: Int): BeerEntityModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBeers(beers: List<BeerEntityModel>)

    /**
     * Uso con fines didácticos, para valorar caso de uso practico en un contexto diferente.
     * Con la actual api considero mejor performance dejar la lista una vez cargada en el viewmodel. Y tirar de interactos o use cases para filtrarla
     */
    @Query("SELECT * FROM beer_table WHERE name LIKE :query")
    suspend fun getAllBeersContain(query: String): List<BeerEntityModel>?
}
