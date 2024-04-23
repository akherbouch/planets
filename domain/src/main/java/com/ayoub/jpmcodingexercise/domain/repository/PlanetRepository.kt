package com.ayoub.jpmcodingexercise.domain.repository

import androidx.paging.PagingData
import com.ayoub.jpmcodingexercise.domain.model.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    suspend fun getPlanets(): Flow<PagingData<Planet>>
    suspend fun getPlanet(id:String): Flow<Planet>
}