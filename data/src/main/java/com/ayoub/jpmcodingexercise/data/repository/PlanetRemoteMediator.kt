package com.ayoub.jpmcodingexercise.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ayoub.jpmcodingexercise.data.database.PlanetDatabase
import com.ayoub.jpmcodingexercise.data.mappers.toEntityList
import com.ayoub.jpmcodingexercise.data.model.RemoteKey
import com.ayoub.jpmcodingexercise.data.network.PlanetApiService
import com.ayoub.jpmcodingexercise.domain.model.Planet
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PlanetRemoteMediator(
    private val db: PlanetDatabase,
    private val api: PlanetApiService
) : RemoteMediator<Int, Planet>(){
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Planet>
    ): MediatorResult {
        try {
            val planetDao = db.planetDao()
            val remoteKeyDao = db.remoteKeyDao()

            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = false
                        )
                    }
                    val remoteKey = remoteKeyDao.getRemoteKey(lastItem.id)
                    val nextPage = remoteKey?.nextPage
                    if (nextPage == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    nextPage
                }
            }

            val response = api.getPlanets(page)
            val planets = response.results
            val endOfPaginationReached = response.next == null

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.clearRemoteKeys()
                    planetDao.clearPlanets()
                }
                val planetEntities = planets.toEntityList()
                val nexPage = if (endOfPaginationReached) null else page + 1
                val keys = planetEntities.map {
                    RemoteKey(planetId = it.id, nextPage = nexPage)
                }
                remoteKeyDao.insertAll(keys)
                planetDao.insertAll(planetEntities)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }
}