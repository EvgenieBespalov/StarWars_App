package com.example.starwars_app.domain.usecase

import androidx.paging.PagingData
import com.example.starwars_app.domain.entity.StarshipEntity
import com.example.starwars_app.domain.repository.StarshipRepository
import kotlinx.coroutines.flow.Flow

class SearchStarshipsUseCase(
    private val repository: StarshipRepository
) {
    suspend operator fun invoke(name: String): Flow<PagingData<StarshipEntity>> = repository.searchStarship(name)
}