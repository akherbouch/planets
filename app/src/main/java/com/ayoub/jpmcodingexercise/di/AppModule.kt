package com.ayoub.jpmcodingexercise.di


import com.ayoub.jpmcodingexercise.data.database.PlanetDatabase
import com.ayoub.jpmcodingexercise.data.network.PlanetApiService
import com.ayoub.jpmcodingexercise.data.repository.PlanetRepositoryImpl
import com.ayoub.jpmcodingexercise.domain.repository.PlanetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlanetModule {

    @Singleton
    @Provides
    fun providesPlanetsRepository(
        planetDatabase: PlanetDatabase,
        planetApiService: PlanetApiService
    ): PlanetRepository {
        return PlanetRepositoryImpl(planetDatabase, planetApiService)
    }
}