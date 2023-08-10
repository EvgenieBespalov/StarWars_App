package com.example.starwars_app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwars_app.data.api.PeopleApi
import com.example.starwars_app.data.converter.ConverterPeople
import com.example.starwars_app.data.paging_source.PeoplePagingSource
import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow

class PeopleRepositoryImpl(
    private val peopleApi: PeopleApi,
    private val converter: ConverterPeople
) : PeopleRepository {

    override suspend fun searchPeople(name: String): Flow<PagingData<PeopleEntity>> =
        Pager(PagingConfig(10)){
            PeoplePagingSource(peopleApi, converter, name)
        }.flow

    override suspend fun getPeopleById(id: String): PeopleEntity = converter.convertModelInEntity(peopleApi.getPeopleById(id))
}