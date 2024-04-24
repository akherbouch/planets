package com.ayoub.jpmcodingexercise.data.network

import com.ayoub.jpmcodingexercise.data.model.PlanetResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetApiService {
    @GET("planets/")
    suspend fun getPlanets(@Query("page") page: Int): PlanetResponse
}