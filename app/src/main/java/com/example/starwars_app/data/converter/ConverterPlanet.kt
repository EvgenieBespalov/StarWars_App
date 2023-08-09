package com.example.starwars_app.data.converter

import com.example.starwars_app.data.model.PlanetApiModel
import com.example.starwars_app.domain.entity.PlanetEntity

class ConverterPlanet {
    fun convertModelInEntity(from: PlanetApiModel): PlanetEntity =
        PlanetEntity(
            id = from.url.replace("[^0-9]".toRegex(), ""),
            name = from.name,
            diameter = from.diameter,
            population = from.population,
            image = "https://starwars-visualguide.com/assets/img/planets/${from.url.replace("[^0-9]".toRegex(), "")}.jpg"
    )
}