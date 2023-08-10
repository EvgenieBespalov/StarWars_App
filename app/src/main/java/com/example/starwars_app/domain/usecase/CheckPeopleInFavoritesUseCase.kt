package com.example.starwars_app.domain.usecase

import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.repository.DatabaseRepository

class CheckPeopleInFavoritesUseCase(
    private val repository: DatabaseRepository
) {
    suspend operator fun invoke(people: PeopleEntity): Boolean = repository.checkSavePeople(people)
}