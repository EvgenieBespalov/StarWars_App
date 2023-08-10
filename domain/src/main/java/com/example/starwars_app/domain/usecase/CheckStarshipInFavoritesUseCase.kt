package com.example.starwars_app.domain.usecase

import com.example.starwars_app.domain.entity.StarshipEntity
import com.example.starwars_app.domain.repository.DatabaseRepository

class CheckStarshipInFavoritesUseCase (
    private val repository: DatabaseRepository
) {
    suspend operator fun invoke(starship: StarshipEntity): Boolean = repository.checkSaveStarship(starship)
}