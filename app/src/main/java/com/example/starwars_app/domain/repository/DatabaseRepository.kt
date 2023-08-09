package com.example.starwars_app.domain.repository

import com.example.starwars_app.domain.entity.DatabaseEntity
import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.entity.PlanetEntity
import com.example.starwars_app.domain.entity.StarshipEntity

interface DatabaseRepository {
    suspend fun savePlanet(planet: PlanetEntity)

    suspend fun savePeople(people: PeopleEntity)

    suspend fun saveStarship(starship: StarshipEntity)

    suspend fun getAll(): List<DatabaseEntity>
}