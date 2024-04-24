package com.ayoub.jpmcodingexercise.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ayoub.jpmcodingexercise.data.C.PAGE_SIZE
import com.ayoub.jpmcodingexercise.data.database.PlanetDatabase
import com.ayoub.jpmcodingexercise.data.network.PlanetApiService
import com.ayoub.jpmcodingexercise.domain.model.Planet
import com.ayoub.jpmcodingexercise.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlanetRepositoryImpl (
    private val db: PlanetDatabase,
    private val api: PlanetApiService
): PlanetRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getPlanets(): Flow<PagingData<Planet>> {
        val pagingSourceFactory = {
            db.planetDao().getPlanets()
        }
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = PlanetRemoteMediator(db, api),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun getPlanet(id: String): Flow<Planet> {
        return  db.planetDao().getPlanet(id)
    }
}