package com.ayoub.jpmcodingexercise.domain.usecase

import androidx.paging.PagingData
import com.ayoub.jpmcodingexercise.domain.base.BaseUseCase
import com.ayoub.jpmcodingexercise.domain.model.Planet
import com.ayoub.jpmcodingexercise.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(
    private val repository: PlanetRepository
) : BaseUseCase<Unit, Flow<PagingData<Planet>>> {

    override suspend fun invoke(input: Unit): Flow<PagingData<Planet>> = repository.getPlanets()
}