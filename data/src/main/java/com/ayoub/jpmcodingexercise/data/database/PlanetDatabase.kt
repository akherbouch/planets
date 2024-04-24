package com.ayoub.jpmcodingexercise.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayoub.jpmcodingexercise.data.model.RemoteKey
import com.ayoub.jpmcodingexercise.domain.model.Planet

@Database(
    entities = [
        Planet::class,
        RemoteKey::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class PlanetDatabase :RoomDatabase(){
    abstract fun planetDao(): PlanetDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}