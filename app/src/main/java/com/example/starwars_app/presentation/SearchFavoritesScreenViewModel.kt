package com.example.starwars_app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars_app.domain.usecase.GetAllFavoritesUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class SearchFavoritesScreenViewModel  (
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase
) : ViewModel() {

    private val _state: MutableLiveData<SearchFavoritesScreenUiState> =
        MutableLiveData(SearchFavoritesScreenUiState.Initial)
    val state: LiveData<SearchFavoritesScreenUiState> = _state

    fun initial() {
        viewModelScope.launch {
            _state.value = SearchFavoritesScreenUiState.Initial
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _state.value = SearchFavoritesScreenUiState.Loading

            try {
                val favorites = getAllFavoritesUseCase()
                _state.value = favorites.let { SearchFavoritesScreenUiState.Content(it) }
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = SearchFavoritesScreenUiState.Error(ex.message)
            }

        }
    }
}