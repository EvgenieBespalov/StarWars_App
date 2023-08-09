package com.example.starwars_app.data.converter

import com.example.starwars_app.data.model.DatabaseModel
import com.example.starwars_app.domain.entity.DatabaseEntity
import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.entity.PlanetEntity
import com.example.starwars_app.domain.entity.StarshipEntity

class ConverterDatabase {
    fun convertPeopleEntityInDatabase(from: PeopleEntity): DatabaseModel =
        DatabaseModel(
            id = from.id,
            name = from.name,
            image = from.image,
            type = "people"
        )

    fun convertPlanetEntityInDatabase(from: PlanetEntity): DatabaseModel =
        DatabaseModel(
            id = from.id,
            name = from.name,
            image = from.image,
            type = "planet"
        )

    fun convertStarshipEntityInDatabase(from: StarshipEntity): DatabaseModel =
        DatabaseModel(
            id = from.id,
            name = from.name,
            image = from.image,
            type = "starship"
        )

    fun converModelInEntity(from: DatabaseModel): DatabaseEntity =
        DatabaseEntity(
            id = from.id,
            name = from.name,
            image = from.image,
            type = from.type
        )
}