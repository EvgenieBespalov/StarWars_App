package com.example.starwars_app.di

import com.example.starwars_app.data.api.PeopleApi
import com.example.starwars_app.data.api.PlanetApi
import com.example.starwars_app.data.api.StarshipApi
import com.example.starwars_app.data.converter.ConverterDatabase
import com.example.starwars_app.data.converter.ConverterPeople
import com.example.starwars_app.data.converter.ConverterPlanet
import com.example.starwars_app.data.converter.ConverterStarship
import com.example.starwars_app.data.dao.DatabaseDAO
import com.example.starwars_app.data.repository.PeopleRepositoryImpl
import com.example.starwars_app.data.paging_source.PlanetPagingSource
import com.example.starwars_app.data.repository.DatabaseRepositoryImpl
import com.example.starwars_app.data.repository.PlanetRepositoryImpl
import com.example.starwars_app.data.repository.StarshipRepositoryImpl
import com.example.starwars_app.domain.repository.DatabaseRepository
import com.example.starwars_app.domain.repository.PeopleRepository
import com.example.starwars_app.domain.repository.PlanetRepository
import com.example.starwars_app.domain.repository.StarshipRepository
import com.example.starwars_app.domain.usecase.*
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

private fun provideStarshipRepositoryImpl(
    starshipApi: StarshipApi,
    converter: ConverterStarship
): StarshipRepository = StarshipRepositoryImpl(starshipApi, converter)

private fun provideDatabaseRepositoryImpl(
    databaseDAO: DatabaseDAO,
    converter: ConverterDatabase
): DatabaseRepository = DatabaseRepositoryImpl(databaseDAO, converter)

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

        single {
            provideStarshipRepositoryImpl(
                starshipApi = get(),
                converter = get()
            )
        }
        factory { SearchStarshipsUseCase(repository = get()) }
        factory { GetStarshipByIdUseCase(repository = get()) }

        single {
            provideDatabaseRepositoryImpl(
                databaseDAO = get(),
                converter = get()
            )
        }
        factory { SavePeopleInFavoritesUseCase(repository = get()) }
        factory { GetAllFavoritesUseCase(repository = get()) }
    }