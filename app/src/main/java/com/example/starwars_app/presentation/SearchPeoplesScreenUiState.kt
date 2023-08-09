package com.example.starwars_app.presentation

import androidx.paging.PagingData
import com.example.starwars_app.domain.entity.PeopleEntity
import kotlinx.coroutines.flow.Flow

sealed interface SearchPeoplesScreenUiState{
    object Initial : SearchPeoplesScreenUiState
    object Loading : SearchPeoplesScreenUiState
    data class Content(val peoples: Flow<PagingData<PeopleEntity>>) : SearchPeoplesScreenUiState
    data class Error(val message: String?) : SearchPeoplesScreenUiState
}