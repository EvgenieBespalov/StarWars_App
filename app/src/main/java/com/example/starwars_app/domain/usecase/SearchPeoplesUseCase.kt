package com.example.starwars_app.domain.usecase

import androidx.paging.PagingData
import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow

class SearchPeoplesUseCase(
    private val repository: PeopleRepository
) {
    suspend operator fun invoke(name: String): Flow<PagingData<PeopleEntity>> = repository.searchPeople(name)
}