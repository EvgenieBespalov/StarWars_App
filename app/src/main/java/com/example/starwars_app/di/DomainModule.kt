package com.example.starwars_app.di

import com.example.starwars_app.data.api.PlanetApi
import com.example.starwars_app.data.converter.ConverterPlanet
import com.example.starwars_app.domain.paging_source.PlanetPagingSource
import com.example.starwars_app.data.repository.PlanetRepositoryImpl
import com.example.starwars_app.domain.repository.PlanetRepository
import com.example.starwars_app.domain.usecase.SearchPlanetsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

private fun providePlanetRepositoryImpl(
    planetApi: PlanetApi,
    converter: ConverterPlanet
): PlanetRepository = PlanetRepositoryImpl(planetApi, converter)

fun provideDomainModule(): Module =
    module {
        single {
            providePlanetRepositoryImpl(
                planetApi = get(),
                converter = get()
            )
        }

        factory { SearchPlanetsUseCase(repository = get()) }
    }