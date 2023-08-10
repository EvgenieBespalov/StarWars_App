package com.example.starwars_app.domain.usecase

import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.entity.PlanetEntity
import com.example.starwars_app.domain.repository.DatabaseRepository

class CheckPlanetInFavoritesUseCase(
    private val repository: DatabaseRepository
) {
    suspend operator fun invoke(planet: PlanetEntity): Boolean = repository.checkSavePlanet(planet)
}