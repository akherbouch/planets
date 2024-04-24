package com.ayoub.jpmcodingexercise.data.datasource.remote


import com.ayoub.jpmcodingexercise.data.model.PlanetResponse
import com.ayoub.jpmcodingexercise.data.network.PlanetApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: PlanetApiService
) {

    suspend fun getPlanet(page: Int): PlanetResponse {
        return  api.getPlanets(page)
    }
}