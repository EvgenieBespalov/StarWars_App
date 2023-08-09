package com.example.starwars_app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.usecase.GetPeopleByIdUseCase
import com.example.starwars_app.domain.usecase.SavePeopleInFavoritesUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class InfoPeopleScreenViewModel (
    private val getPeopleByIdUseCase: GetPeopleByIdUseCase,
    private val savePeopleInFavoritesUseCase: SavePeopleInFavoritesUseCase
) : ViewModel() {

    private val _state: MutableLiveData<InfoPeopleScreenUiState> =
        MutableLiveData(InfoPeopleScreenUiState.Initial)
    val state: LiveData<InfoPeopleScreenUiState> = _state

    fun initial() {
        viewModelScope.launch {
            _state.value = InfoPeopleScreenUiState.Initial
        }
    }

    fun getPeopleById(id: String) {
        viewModelScope.launch {
            _state.value = InfoPeopleScreenUiState.Loading

            try {
                val people = getPeopleByIdUseCase(id)
                _state.value = people.let { InfoPeopleScreenUiState.Content(it) }
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = InfoPeopleScreenUiState.Error(ex.message)
            }

        }
    }

    fun savePeople(people: PeopleEntity) {
        viewModelScope.launch {
            try {
                savePeopleInFavoritesUseCase(people)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = InfoPeopleScreenUiState.Error(ex.message)
            }

        }
    }
}