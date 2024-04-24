package com.ayoub.jpmcodingexercise.data.di

import com.ayoub.jpmcodingexercise.data.C
import com.ayoub.jpmcodingexercise.data.network.NetworkUtil
import com.ayoub.jpmcodingexercise.data.network.PlanetApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(C.BASE_URL)
            .addConverterFactory(
                Json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()))
            .client(NetworkUtil.trustAllOkHttpClient())
            .build()

    @Singleton
    @Provides
    fun providesPlanetApiService(retrofit: Retrofit): PlanetApiService =
        retrofit.create(PlanetApiService::class.java)
}