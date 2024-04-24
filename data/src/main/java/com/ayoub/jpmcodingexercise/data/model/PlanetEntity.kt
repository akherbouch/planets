package com.ayoub.jpmcodingexercise.data.model


import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "planets")
data class PlanetEntity(
    @PrimaryKey
    var id: String,
    val climate: String?,
    val created: String?,
    val diameter: String?,
    val gravity: String?,
    val name: String?,
    val orbitalPeriod: String?,
    val population: String?,
    val rotationPeriod: String?,
    val surfaceWater: String?,
    val terrain: String?,
)
