package com.example.starwars_app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars_app.domain.usecase.SearchPlanetsUseCase
import com.example.starwars_app.domain.usecase.SearchStarshipsUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class SearchStarshipsScreenViewModel  (
    private val searchStarshipsUseCase: SearchStarshipsUseCase
) : ViewModel() {

    private val _state: MutableLiveData<SearchStarshipsScreenUiState> =
        MutableLiveData(SearchStarshipsScreenUiState.Initial)
    val state: LiveData<SearchStarshipsScreenUiState> = _state

    fun initial() {
        viewModelScope.launch {
            _state.value = SearchStarshipsScreenUiState.Initial
        }
    }

    fun searchStarships(name: String) {
        viewModelScope.launch {
            _state.value = SearchStarshipsScreenUiState.Loading

            try {
                val starshipss = searchStarshipsUseCase(name)
                _state.value = starshipss.let { SearchStarshipsScreenUiState.Content(it) }
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = SearchStarshipsScreenUiState.Error(ex.message)
            }

        }
    }
}