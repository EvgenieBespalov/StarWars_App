package com.example.starwars_app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwars_app.data.api.StarshipApi
import com.example.starwars_app.data.converter.ConverterStarship
import com.example.starwars_app.data.paging_source.StarshipPagingSource
import com.example.starwars_app.domain.entity.StarshipEntity
import com.example.starwars_app.domain.repository.StarshipRepository
import kotlinx.coroutines.flow.Flow

class StarshipRepositoryImpl (
    private val starshipApi: StarshipApi,
    private val converter: ConverterStarship
) : StarshipRepository {

    override suspend fun searchStarship(name: String): Flow<PagingData<StarshipEntity>> =
        Pager(PagingConfig(10)){
            StarshipPagingSource(starshipApi, converter, name)
        }.flow

    override suspend fun getStarshipById(id: String): StarshipEntity = converter.convertModelInEntity(starshipApi.getPlanetById(id))
}