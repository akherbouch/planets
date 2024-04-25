package com.ayoub.jpmcodingexercise.data

import com.ayoub.jpmcodingexercise.data.model.PlanetDTO
import com.ayoub.jpmcodingexercise.data.model.PlanetResponse
import com.ayoub.jpmcodingexercise.domain.model.Planet


val planetDTOs = listOf(PlanetDTO(
    name = "Mirial",
    rotationPeriod = "unknown",
    orbitalPeriod = "unknown",
    diameter = "unknown",
    climate = "unknown",
    gravity = "unknown",
    terrain = "deserts",
    surfaceWater = "unknown",
    population = "unknown",
    residents = listOf(
        "https://swapi.dev/api/people/64/",
        "https://swapi.dev/api/people/65/"
    ),
    films = listOf(),
    created = "2014-12-20T16:44:46.318000Z",
    edited = "2014-12-20T20:58:18.508000Z",
    url = "https://swapi.dev/api/planets/51/"
),PlanetDTO(
    name = "Serenno",
    rotationPeriod = "unknown",
    orbitalPeriod = "unknown",
    diameter = "unknown",
    climate = "unknown",
    gravity = "unknown",
    terrain = "rainforests, rivers, mountains",
    surfaceWater = "unknown",
    population = "unknown",
    residents = listOf(
        "https://swapi.dev/api/people/67/"
    ),
    films = listOf(),
    created = "2014-12-20T16:52:13.357000Z",
    edited = "2014-12-20T20:58:18.510000Z",
    url = "https://swapi.dev/api/planets/52/"
))

val planetResponse = PlanetResponse(
    count = 60,
    next = null,
    previous = null,
    results = planetDTOs,
)


val planets = listOf(
    Planet(
        id = "1",
        name = "Tatooine",
        rotationPeriod = "23",
        orbitalPeriod = "304",
        diameter = "10465",
        climate = "arid",
        gravity = "1 standard",
        terrain = "desert",
        surfaceWater = "1",
        population = "200000",
        created = "2014-12-09T13:50:49.641000Z"
    ), Planet(
        id = "2",
        name = "Yavin IV",
        rotationPeriod = "24",
        orbitalPeriod = "4818",
        diameter = "10200",
        climate = "temperate, tropical",
        gravity = "1 standard",
        terrain = "jungle, rainforests",
        surfaceWater = "8",
        population = "1000",
        created = "2014-12-10T11:37:19.144000Z"
    ),
)