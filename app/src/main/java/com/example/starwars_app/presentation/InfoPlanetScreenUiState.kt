package com.example.starwars_app.presentation

import com.example.starwars_app.domain.entity.PlanetEntity

sealed interface InfoPlanetScreenUiState{
    object Initial : InfoPlanetScreenUiState
    object Loading : InfoPlanetScreenUiState
    data class Content(val planet: PlanetEntity, val checkFavorites: Boolean) : InfoPlanetScreenUiState
    data class Error(val message: String?) : InfoPlanetScreenUiState
}