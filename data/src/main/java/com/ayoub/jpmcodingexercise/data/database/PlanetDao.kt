package com.ayoub.jpmcodingexercise.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayoub.jpmcodingexercise.domain.model.Planet
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(planets: List<Planet>)

    @Query("SELECT * FROM planets")
    fun getPlanets(): PagingSource<Int, Planet>

    @Query("SELECT * FROM planets WHERE id=:id")
    fun getPlanet(id:String): Flow<Planet>

    @Query("DELETE FROM planets")
    suspend fun clearPlanets()
}


