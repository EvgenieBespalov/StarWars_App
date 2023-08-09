package com.example.starwars_app.presentation

import com.example.starwars_app.domain.entity.DatabaseEntity


sealed interface SearchFavoritesScreenUiState{
    object Initial : SearchFavoritesScreenUiState
    object Loading : SearchFavoritesScreenUiState
    data class Content(val favorites: List<DatabaseEntity>) : SearchFavoritesScreenUiState
    data class Error(val message: String?) : SearchFavoritesScreenUiState
}