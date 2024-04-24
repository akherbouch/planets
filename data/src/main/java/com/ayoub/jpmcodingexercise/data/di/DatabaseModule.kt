package com.ayoub.jpmcodingexercise.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ayoub.jpmcodingexercise.data.database.PlanetDao
import com.ayoub.jpmcodingexercise.data.database.PlanetDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesPlanetDatabase(@ApplicationContext context: Context): PlanetDatabase {
        return Room.databaseBuilder(
            context,
            PlanetDatabase::class.java,
            "planet_database"
        ).build()
    }

}