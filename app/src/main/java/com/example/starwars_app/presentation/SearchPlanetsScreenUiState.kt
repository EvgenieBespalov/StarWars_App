package com.example.starwars_app.presentation

import androidx.paging.PagingData
import com.example.starwars_app.domain.entity.PlanetEntity
import kotlinx.coroutines.flow.Flow

sealed interface SearchPlanetsScreenUiState{
    object Initial : SearchPlanetsScreenUiState
    object Loading : SearchPlanetsScreenUiState
    data class Content(val planets: Flow<PagingData<PlanetEntity>>) : SearchPlanetsScreenUiState
    data class Error(val message: String?) : SearchPlanetsScreenUiState
}