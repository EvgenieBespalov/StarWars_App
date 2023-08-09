package com.example.starwars_app.data.repository

import com.example.starwars_app.data.converter.ConverterDatabase
import com.example.starwars_app.data.dao.DatabaseDAO
import com.example.starwars_app.domain.entity.DatabaseEntity
import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.entity.PlanetEntity
import com.example.starwars_app.domain.entity.StarshipEntity
import com.example.starwars_app.domain.repository.DatabaseRepository


class DatabaseRepositoryImpl(
    private val databaseDAO: DatabaseDAO,
    private val converter: ConverterDatabase
) : DatabaseRepository {

    override fun savePlanet(planet: PlanetEntity) {
        databaseDAO.save(converter.convertPlanetEntityInDatabase(planet))
    }

    override fun savePeople(people: PeopleEntity) {
        databaseDAO.save(converter.convertPeopleEntityInDatabase(people))
    }

    override fun saveStarship(starship: StarshipEntity) {
        databaseDAO.save(converter.convertStarshipEntityInDatabase(starship))
    }

    override fun getAll(): List<DatabaseEntity> = databaseDAO.getAll().map { converter.converModelInEntity(it) }
}