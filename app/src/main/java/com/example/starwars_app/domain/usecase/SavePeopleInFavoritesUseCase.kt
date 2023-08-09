package com.example.starwars_app.domain.usecase

import androidx.paging.PagingData
import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow

class SavePeopleInFavoritesUseCase(
    private val repository: DatabaseRepository
) {
    suspend operator fun invoke(peopleEntity: PeopleEntity) {
        repository.savePeople(peopleEntity)
    }
}