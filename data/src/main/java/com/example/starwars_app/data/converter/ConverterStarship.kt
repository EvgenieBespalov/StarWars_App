package com.example.starwars_app.data.converter

import com.example.starwars_app.data.model.StarshipApiModel
import com.example.starwars_app.domain.entity.StarshipEntity


class ConverterStarship {
    fun convertModelInEntity(from: StarshipApiModel): StarshipEntity =
        StarshipEntity(
            id = from.url.replace("[^0-9]".toRegex(), ""),
            name = from.name,
            model = from.model,
            manufacturer = from.manufacturer,
            passengers = from.passengers,
            image = "https://starwars-visualguide.com/assets/img/starships/${
                from.url.replace(
                    "[^0-9]".toRegex(),
                    ""
                )
            }.jpg"
        )
}