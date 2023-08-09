package com.example.starwars_app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars_app.domain.usecase.SearchPeoplesUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class SearchPeoplesScreenViewModel  (
    private val searchPeoplesUseCase: SearchPeoplesUseCase
) : ViewModel() {

    private val _state: MutableLiveData<SearchPeoplesScreenUiState> =
        MutableLiveData(SearchPeoplesScreenUiState.Initial)
    val state: LiveData<SearchPeoplesScreenUiState> = _state

    fun initial() {
        viewModelScope.launch {
            _state.value = SearchPeoplesScreenUiState.Initial
        }
    }

    fun searchPeoples(name: String) {
        viewModelScope.launch {
            _state.value = SearchPeoplesScreenUiState.Loading

            try {
                val peoples = searchPeoplesUseCase(name)
                _state.value = peoples.let { SearchPeoplesScreenUiState.Content(it) }
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = SearchPeoplesScreenUiState.Error(ex.message)
            }

        }
    }
}