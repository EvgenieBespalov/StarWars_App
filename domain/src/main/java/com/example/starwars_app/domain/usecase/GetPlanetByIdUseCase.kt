package com.example.starwars_app.domain.usecase

import androidx.paging.PagingData
import com.example.starwars_app.domain.entity.PlanetEntity
import com.example.starwars_app.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow

class GetPlanetByIdUseCase(
    private val repository: PlanetRepository
) {
    suspend operator fun invoke(id: String): PlanetEntity = repository.getPlanetById(id)
}