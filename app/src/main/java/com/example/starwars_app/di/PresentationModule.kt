package com.example.starwars_app.di

import com.example.starwars_app.presentation.InfoPlanetScreenViewModel
import com.example.starwars_app.presentation.SearchPlanetsScreenViewModel
import com.example.starwars_app.screen.InfoPlanetScreen
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
                getPlanetByIdUseCase = get()
            )
        }
    }