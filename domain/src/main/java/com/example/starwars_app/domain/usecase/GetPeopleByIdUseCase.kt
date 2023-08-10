package com.example.starwars_app.domain.usecase

import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.repository.PeopleRepository


class GetPeopleByIdUseCase(
    private val repository: PeopleRepository
) {
    suspend operator fun invoke(id: String): PeopleEntity = repository.getPeopleById(id)
}