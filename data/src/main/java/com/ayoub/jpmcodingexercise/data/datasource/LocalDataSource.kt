package com.ayoub.jpmcodingexercise.data.datasource

import androidx.paging.PagingSource
import com.ayoub.jpmcodingexercise.data.database.PlanetDao
import com.ayoub.jpmcodingexercise.data.model.PlanetEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val planetDao: PlanetDao
) {

    suspend fun insertAll(planets: List<PlanetEntity>) = planetDao.insertAll(planets)

    fun getPlanets(): PagingSource<Int, PlanetEntity> = planetDao.getPlanets()

    fun getPlanet(id:String): Flow<PlanetEntity> = planetDao.getPlanet(id)

    suspend fun clearPlanets() = planetDao.clearPlanets()
}