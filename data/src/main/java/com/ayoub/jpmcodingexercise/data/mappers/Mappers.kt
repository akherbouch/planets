package com.ayoub.jpmcodingexercise.data.mappers

import com.ayoub.jpmcodingexercise.data.model.PlanetDTO
import com.ayoub.jpmcodingexercise.domain.model.Planet

fun PlanetDTO.mapToPlanet() = Planet(
    id = this.name,
    name = this.name,
    climate = this.climate,
    created = this.created,
    diameter = this.diameter,
    gravity = this.gravity,
    orbitalPeriod = this.orbitalPeriod,
    population = this.population,
    rotationPeriod = this.rotationPeriod,
    surfaceWater = this.surfaceWater,
    terrain = this.terrain
)

fun List<PlanetDTO>.toEntityList(): List<Planet> = this.map { it.mapToPlanet() }
