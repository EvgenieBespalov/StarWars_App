package com.example.starwars_app.data.api

import com.example.starwars_app.data.model.PeopleApiModel
import com.example.starwars_app.data.model.PeoplesListApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleApi{
    @GET("/api/people/")
    suspend fun searchPeople(@Query("search") name: String, @Query("page") page: String): Response<PeoplesListApiModel>

    @GET("/api/people/{id}/")
    suspend fun getPeopleById(@Path("id") id: String): PeopleApiModel
}