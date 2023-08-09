package com.example.starwars_app.domain.repository

import com.example.starwars_app.domain.entity.DatabaseEntity
import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.entity.PlanetEntity
import com.example.starwars_app.domain.entity.StarshipEntity

interface DatabaseRepository {
    fun savePlanet(planet: PlanetEntity)

    fun savePeople(people: PeopleEntity)

    fun saveStarship(starship: StarshipEntity)

    fun getAll(): List<DatabaseEntity>
}