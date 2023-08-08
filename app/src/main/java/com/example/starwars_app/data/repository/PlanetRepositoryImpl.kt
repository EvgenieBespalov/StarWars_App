package com.example.starwars_app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwars_app.data.api.PlanetApi
import com.example.starwars_app.data.converter.ConverterPlanet
import com.example.starwars_app.domain.paging_source.PlanetPagingSource
import com.example.starwars_app.domain.entity.PlanetEntity
import com.example.starwars_app.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow

class PlanetRepositoryImpl(
    private val planetApi: PlanetApi,
    private val converter: ConverterPlanet
) : PlanetRepository {

    override suspend fun searchPlanet(name: String, page: String): Flow<PagingData<PlanetEntity>> =
        Pager(PagingConfig(10)){
            PlanetPagingSource(planetApi, converter, name)
        }.flow



    override suspend fun getPlanetById(id: String): PlanetEntity = converter.convertModelInEntity(planetApi.getPlanetById(id))
}