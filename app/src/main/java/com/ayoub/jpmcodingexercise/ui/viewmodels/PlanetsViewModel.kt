package com.ayoub.jpmcodingexercise.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ayoub.jpmcodingexercise.domain.model.Planet
import com.ayoub.jpmcodingexercise.domain.usecase.GetPlanetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase
) : ViewModel() {

    private val _planets = MutableStateFlow<PagingData<Planet>>(value = PagingData.empty())
    val planets: StateFlow<PagingData<Planet>> get() = _planets

    init {
        loadPlanets()
    }

    private fun loadPlanets(){
        viewModelScope.launch {
            getPlanetsUseCase(Unit)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect{
                    _planets.value = it
                }
        }
    }
}