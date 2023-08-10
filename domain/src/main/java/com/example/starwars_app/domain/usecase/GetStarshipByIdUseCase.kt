package com.example.starwars_app.domain.usecase

import com.example.starwars_app.domain.entity.StarshipEntity
import com.example.starwars_app.domain.repository.StarshipRepository


class GetStarshipByIdUseCase(
    private val repository: StarshipRepository
) {
    suspend operator fun invoke(id: String): StarshipEntity = repository.getStarshipById(id)
}