package com.example.starwars_app.domain.repository

import androidx.paging.PagingData
import com.example.starwars_app.domain.entity.PeopleEntity
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    suspend fun searchPeople(name: String): Flow<PagingData<PeopleEntity>>

    suspend fun getPeopleById(id: String): PeopleEntity
}