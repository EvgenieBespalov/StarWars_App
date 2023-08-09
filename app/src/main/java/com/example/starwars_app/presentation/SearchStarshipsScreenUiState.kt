package com.example.starwars_app.presentation

import androidx.paging.PagingData
import com.example.starwars_app.domain.entity.StarshipEntity
import kotlinx.coroutines.flow.Flow

sealed interface SearchStarshipsScreenUiState{
    object Initial : SearchStarshipsScreenUiState
    object Loading : SearchStarshipsScreenUiState
    data class Content(val starships: Flow<PagingData<StarshipEntity>>) : SearchStarshipsScreenUiState
    data class Error(val message: String?) : SearchStarshipsScreenUiState
}