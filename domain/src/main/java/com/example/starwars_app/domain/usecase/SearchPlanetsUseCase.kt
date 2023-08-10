package com.example.starwars_app.domain.usecase

import androidx.paging.PagingData
import com.example.starwars_app.domain.entity.PlanetEntity
import com.example.starwars_app.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow

class SearchPlanetsUseCase(
    private val repository: PlanetRepository
) {
    suspend operator fun invoke(name: String): Flow<PagingData<PlanetEntity>> = repository.searchPlanet(name)
}