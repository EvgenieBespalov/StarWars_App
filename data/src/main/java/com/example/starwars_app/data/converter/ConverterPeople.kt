package com.example.starwars_app.data.converter

import com.example.starwars_app.data.model.PeopleApiModel
import com.example.starwars_app.domain.entity.PeopleEntity


class ConverterPeople {
    fun convertModelInEntity(from: PeopleApiModel): PeopleEntity =
        PeopleEntity(
            id = from.url.replace("[^0-9]".toRegex(), ""),
            name = from.name,
            gender = from.gender,
            countOfStarhips = from.starships.size.toString(),
            image = "https://starwars-visualguide.com/assets/img/characters/${from.url.replace("[^0-9]".toRegex(), "")}.jpg"
        )
}