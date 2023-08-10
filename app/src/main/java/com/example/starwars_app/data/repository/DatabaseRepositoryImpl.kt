package com.example.starwars_app.data.repository

import com.example.starwars_app.data.converter.ConverterDatabase
import com.example.starwars_app.data.dao.DatabaseDAO
import com.example.starwars_app.domain.entity.DatabaseEntity
import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.entity.PlanetEntity
import com.example.starwars_app.domain.entity.StarshipEntity
import com.example.starwars_app.domain.repository.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DatabaseRepositoryImpl(
    private val databaseDAO: DatabaseDAO,
    private val converter: ConverterDatabase
) : DatabaseRepository {

    override suspend fun savePlanet(planet: PlanetEntity) {
        withContext(Dispatchers.IO) {
            databaseDAO.save(converter.convertPlanetEntityInDatabase(planet))
        }
    }

    override suspend fun savePeople(people: PeopleEntity) {
        withContext(Dispatchers.IO) {
            databaseDAO.save(converter.convertPeopleEntityInDatabase(people))
        }
    }

    override suspend fun saveStarship(starship: StarshipEntity) {
        withContext(Dispatchers.IO) {
            databaseDAO.save(converter.convertStarshipEntityInDatabase(starship))
        }
    }

    override suspend fun getAll(): List<DatabaseEntity> {
        return withContext(Dispatchers.IO) {
             databaseDAO.getAll().map { converter.converModelInEntity(it) }
        }
    }

    override suspend fun checkSavePlanet(planet: PlanetEntity): Boolean {
        return withContext(Dispatchers.IO) {
            val planetModel = converter.convertPlanetEntityInDatabase(planet)

            when(databaseDAO.checkSave(planetModel.id, planetModel.type)){
                null -> false
                else -> true
            }
        }
    }

    override suspend fun checkSavePeople(people: PeopleEntity): Boolean {
        return withContext(Dispatchers.IO) {
            val peopleModel = converter.convertPeopleEntityInDatabase(people)

            when(databaseDAO.checkSave(peopleModel.id, peopleModel.type)){
                null -> false
                else -> true
            }
        }
    }

    override suspend fun checkSaveStarship(starship: StarshipEntity): Boolean {
        return withContext(Dispatchers.IO) {
            val starshipModel = converter.convertStarshipEntityInDatabase(starship)

            when(databaseDAO.checkSave(starshipModel.id, starshipModel.type)){
                null -> false
                else -> true
            }
        }
    }
}