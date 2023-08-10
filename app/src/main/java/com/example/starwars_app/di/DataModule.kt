package com.example.starwars_app.di

import com.example.starwars_app.data.converter.ConverterDatabase
import com.example.starwars_app.data.converter.ConverterPeople
import com.example.starwars_app.data.converter.ConverterPlanet
import com.example.starwars_app.data.converter.ConverterStarship
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideDataModule(): Module =
    module {
        factory { ConverterPlanet() }
        factory { ConverterPeople() }
        factory { ConverterStarship() }
        factory { ConverterDatabase() }
    }