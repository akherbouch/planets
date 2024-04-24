package com.ayoub.jpmcodingexercise.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayoub.jpmcodingexercise.data.model.PlanetEntity

@Database(
    entities = [
        PlanetEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class PlanetDatabase :RoomDatabase(){
    abstract fun planetDao(): PlanetDao
}