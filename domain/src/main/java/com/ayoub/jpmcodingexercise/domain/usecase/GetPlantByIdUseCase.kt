package com.ayoub.jpmcodingexercise.domain.usecase

import com.ayoub.jpmcodingexercise.domain.base.BaseUseCase
import com.ayoub.jpmcodingexercise.domain.model.Planet
import com.ayoub.jpmcodingexercise.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlantByIdUseCase @Inject constructor(
    private val repository: PlanetRepository
) : BaseUseCase<String, Flow<Planet>> {

    override suspend fun invoke(id: String): Flow<Planet> = repository.getPlanet(id)
}