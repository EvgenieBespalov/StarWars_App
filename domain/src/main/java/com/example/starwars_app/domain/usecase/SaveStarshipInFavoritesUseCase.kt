package com.example.starwars_app.domain.usecase

import com.example.starwars_app.domain.entity.StarshipEntity
import com.example.starwars_app.domain.repository.DatabaseRepository

class SaveStarshipInFavoritesUseCase(
    private val repository: DatabaseRepository
) {
    suspend operator fun invoke(starshipEntity: StarshipEntity) {
        repository.saveStarship(starshipEntity)
    }
}