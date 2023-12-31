package com.example.starwars_app.di

import com.example.starwars_app.presentation.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun providePresentationModule(): Module =
    module {
        viewModel {
            SearchPlanetsScreenViewModel(
                searchPlanetsUseCase = get()
            )
        }
        viewModel {
            InfoPlanetScreenViewModel(
                getPlanetByIdUseCase = get(),
                savePlanetInFavoritesUseCase = get(),
                checkPlanetInFavoritesUseCase = get()
            )
        }

        viewModel {
            SearchPeoplesScreenViewModel(
                searchPeoplesUseCase = get()
            )
        }
        viewModel {
            InfoPeopleScreenViewModel(
                getPeopleByIdUseCase = get(),
                savePeopleInFavoritesUseCase = get(),
                checkPeopleInFavoritesUseCase = get()
            )
        }

        viewModel {
            SearchStarshipsScreenViewModel(
                searchStarshipsUseCase = get()
            )
        }

        viewModel {
            InfoStarshipScreenViewModel(
                getStarshipByIdUseCase = get(),
                saveStarshipInFavoritesUseCase = get(),
                checkStarshipInFavoritesUseCase = get()
            )
        }

        viewModel {
            SearchFavoritesScreenViewModel(
                getAllFavoritesUseCase = get()
            )
        }
    }