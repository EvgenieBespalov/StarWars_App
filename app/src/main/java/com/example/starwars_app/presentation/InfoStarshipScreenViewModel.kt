package com.example.starwars_app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.entity.StarshipEntity
import com.example.starwars_app.domain.usecase.GetStarshipByIdUseCase
import com.example.starwars_app.domain.usecase.SaveStarshipInFavoritesUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class InfoStarshipScreenViewModel(
    private val getStarshipByIdUseCase: GetStarshipByIdUseCase,
    private val saveStarshipInFavoritesUseCase: SaveStarshipInFavoritesUseCase
) : ViewModel() {

    private val _state: MutableLiveData<InfoStarshipScreenUiState> =
        MutableLiveData(InfoStarshipScreenUiState.Initial)
    val state: LiveData<InfoStarshipScreenUiState> = _state

    fun initial() {
        viewModelScope.launch {
            _state.value = InfoStarshipScreenUiState.Initial
        }
    }

    fun getStarshipBy(id: String) {
        viewModelScope.launch {
            _state.value = InfoStarshipScreenUiState.Loading

            try {
                val starship = getStarshipByIdUseCase(id)
                _state.value = starship.let { InfoStarshipScreenUiState.Content(it) }
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = InfoStarshipScreenUiState.Error(ex.message)
            }

        }
    }

    fun saveStarship(starship: StarshipEntity) {
        viewModelScope.launch {
            try {
                saveStarshipInFavoritesUseCase(starship)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = InfoStarshipScreenUiState.Error(ex.message)
            }

        }
    }
}