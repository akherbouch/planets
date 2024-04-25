package com.ayoub.jpmcodingexercise.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayoub.jpmcodingexercise.domain.model.Planet
import com.ayoub.jpmcodingexercise.domain.usecase.GetPlantByIdUseCase
import com.ayoub.jpmcodingexercise.ui.navigation.Destinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(
    private val getPlantByIdUseCase: GetPlantByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val planetId = savedStateHandle.get<String>(Destinations.Planets.PlanetDetails.key) ?: ""
    private val _planet = MutableStateFlow(Planet(id = planetId))
    val planet: StateFlow<Planet> get() = _planet


    init {
        if (planetId.isNotEmpty()) {
            loadPlanetById(planetId)
        }
    }

    private fun  loadPlanetById(planetId: String){
        viewModelScope.launch {
            getPlantByIdUseCase(planetId)
                .distinctUntilChanged()
                .collect{
                    _planet.value = it
                }
        }
    }
}