package com.example.starwars_app.presentation

import com.example.starwars_app.domain.entity.StarshipEntity


sealed interface InfoStarshipScreenUiState{
    object Initial : InfoStarshipScreenUiState
    object Loading : InfoStarshipScreenUiState
    data class Content(val starship: StarshipEntity) : InfoStarshipScreenUiState
    data class Error(val message: String?) : InfoStarshipScreenUiState
}