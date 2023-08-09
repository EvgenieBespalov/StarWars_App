package com.example.starwars_app.data.api

import com.example.starwars_app.data.model.PlanetApiModel
import com.example.starwars_app.data.model.PlanetsListApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlanetApi {
   // @GET("/api/planets/?search={name}&page={page}")
    @GET("/api/planets/")
    suspend fun searchPlanet(@Query("search") name: String, @Query("page") page: String): Response<PlanetsListApiModel>

    @GET("/api/planets/{id}/")
    suspend fun getPlanetById(@Path("id") id: String): PlanetApiModel
}