package com.example.starwars_app.di

import com.example.starwars_app.presentation.SearchPlanetsScreenViewModel
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

    }