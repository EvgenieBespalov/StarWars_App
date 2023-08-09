package com.example.starwars_app.domain.usecase

import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.entity.PlanetEntity
import com.example.starwars_app.domain.repository.DatabaseRepository

class SavePlanetInFavoritesUseCase(
    private val repository: DatabaseRepository
) {
    suspend operator fun invoke(planetEntity: PlanetEntity) {
        repository.savePlanet(planetEntity)
    }
}