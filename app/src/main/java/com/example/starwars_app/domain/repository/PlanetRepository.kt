package com.example.starwars_app.domain.repository

import androidx.paging.PagingData
import com.example.starwars_app.domain.entity.PlanetEntity
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    suspend fun searchPlanet(name: String, page: String): Flow<PagingData<PlanetEntity>>

    suspend fun getPlanetById(id: String): PlanetEntity
}