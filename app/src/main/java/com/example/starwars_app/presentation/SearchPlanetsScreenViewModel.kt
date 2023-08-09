package com.example.starwars_app.presentation

import android.service.controls.ControlsProviderService
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars_app.domain.usecase.SearchPlanetsUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class SearchPlanetsScreenViewModel (
    private val searchPlanetsUseCase: SearchPlanetsUseCase
) : ViewModel() {

    private val _state: MutableLiveData<SearchPlanetsScreenUiState> =
        MutableLiveData(SearchPlanetsScreenUiState.Initial)
    val state: LiveData<SearchPlanetsScreenUiState> = _state

    fun initial() {
        viewModelScope.launch {
            _state.value = SearchPlanetsScreenUiState.Initial
        }
    }

    fun searchPlanets(name: String) {
        viewModelScope.launch {
            _state.value = SearchPlanetsScreenUiState.Loading

            try {
                val planets = searchPlanetsUseCase(name)
                _state.value = planets.let { SearchPlanetsScreenUiState.Content(it) }
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = SearchPlanetsScreenUiState.Error(ex.message)
            }

        }
    }
}