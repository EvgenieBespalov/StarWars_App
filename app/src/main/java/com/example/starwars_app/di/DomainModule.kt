package com.example.starwars_app.di

import com.example.starwars_app.data.api.PeopleApi
import com.example.starwars_app.data.api.PlanetApi
import com.example.starwars_app.data.converter.ConverterPeople
import com.example.starwars_app.data.converter.ConverterPlanet
import com.example.starwars_app.data.repository.PeopleRepositoryImpl
import com.example.starwars_app.domain.paging_source.PlanetPagingSource
import com.example.starwars_app.data.repository.PlanetRepositoryImpl
import com.example.starwars_app.domain.repository.PeopleRepository
import com.example.starwars_app.domain.repository.PlanetRepository
import com.example.starwars_app.domain.usecase.GetPeopleByIdUseCase
import com.example.starwars_app.domain.usecase.GetPlanetByIdUseCase
import com.example.starwars_app.domain.usecase.SearchPeoplesUseCase
import com.example.starwars_app.domain.usecase.SearchPlanetsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

private fun providePlanetRepositoryImpl(
    planetApi: PlanetApi,
    converter: ConverterPlanet
): PlanetRepository = PlanetRepositoryImpl(planetApi, converter)

private fun providePeopleRepositoryImpl(
    peopleApi: PeopleApi,
    converter: ConverterPeople
): PeopleRepository = PeopleRepositoryImpl(peopleApi, converter)

fun provideDomainModule(): Module =
    module {
        single {
            providePlanetRepositoryImpl(
                planetApi = get(),
                converter = get()
            )
        }
        factory { SearchPlanetsUseCase(repository = get()) }
        factory { GetPlanetByIdUseCase(repository = get()) }

        single {
            providePeopleRepositoryImpl(
                peopleApi = get(),
                converter = get()
            )
        }
        factory { SearchPeoplesUseCase(repository = get()) }
        factory { GetPeopleByIdUseCase(repository = get()) }
    }