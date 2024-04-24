package com.ayoub.jpmcodingexercise.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayoub.jpmcodingexercise.data.model.PlanetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(planets: List<PlanetEntity>)

    @Query("SELECT * FROM planets")
    fun getPlanets(): PagingSource<Int, PlanetEntity>

    @Query("SELECT * FROM planets WHERE id=:id")
    fun getPlanet(id:String): Flow<PlanetEntity>

    @Query("DELETE FROM planets")
    suspend fun clearPlanets()
}


