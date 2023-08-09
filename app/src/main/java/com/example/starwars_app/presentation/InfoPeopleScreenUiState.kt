package com.example.starwars_app.presentation

import com.example.starwars_app.domain.entity.PeopleEntity

sealed interface InfoPeopleScreenUiState{
    object Initial : InfoPeopleScreenUiState
    object Loading : InfoPeopleScreenUiState
    data class Content(val people: PeopleEntity) : InfoPeopleScreenUiState
    data class Error(val message: String?) : InfoPeopleScreenUiState
}