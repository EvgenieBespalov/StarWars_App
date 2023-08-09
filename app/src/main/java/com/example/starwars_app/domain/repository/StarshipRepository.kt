package com.example.starwars_app.domain.repository

import androidx.paging.PagingData
import com.example.starwars_app.domain.entity.StarshipEntity
import kotlinx.coroutines.flow.Flow

interface StarshipRepository{
    suspend fun searchStarship(name: String): Flow<PagingData<StarshipEntity>>

    suspend fun getStarshipById(id: String): StarshipEntity
}