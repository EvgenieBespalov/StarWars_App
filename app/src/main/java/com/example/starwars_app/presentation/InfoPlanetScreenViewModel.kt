package com.example.starwars_app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.entity.PlanetEntity
import com.example.starwars_app.domain.usecase.GetPlanetByIdUseCase
import com.example.starwars_app.domain.usecase.SavePlanetInFavoritesUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class InfoPlanetScreenViewModel (
    private val getPlanetByIdUseCase: GetPlanetByIdUseCase,
    private val savePlanetInFavoritesUseCase: SavePlanetInFavoritesUseCase
) : ViewModel() {

    private val _state: MutableLiveData<InfoPlanetScreenUiState> =
        MutableLiveData(InfoPlanetScreenUiState.Initial)
    val state: LiveData<InfoPlanetScreenUiState> = _state

    fun initial() {
        viewModelScope.launch {
            _state.value = InfoPlanetScreenUiState.Initial
        }
    }

    fun getPlanetById(id: String) {
        viewModelScope.launch {
            _state.value = InfoPlanetScreenUiState.Loading

            try {
                val planet = getPlanetByIdUseCase(id)
                _state.value = planet.let { InfoPlanetScreenUiState.Content(it) }
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = InfoPlanetScreenUiState.Error(ex.message)
            }

        }
    }

    fun savePlanet(planet: PlanetEntity) {
        viewModelScope.launch {
            try {
                savePlanetInFavoritesUseCase(planet)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = InfoPlanetScreenUiState.Error(ex.message)
            }

        }
    }
}