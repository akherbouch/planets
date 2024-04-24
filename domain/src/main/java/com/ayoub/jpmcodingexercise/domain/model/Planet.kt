package com.ayoub.jpmcodingexercise.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "planets")
data class Planet(
    @PrimaryKey
    var id: String,
    val climate: String? = null,
    val created: String? = null,
    val diameter: String? = null,
    val gravity: String? = null,
    val name: String? = null,
    val orbitalPeriod: String? = null,
    val population: String? = null,
    val rotationPeriod: String? = null,
    val surfaceWater: String? = null,
    val terrain: String? = null,
)
