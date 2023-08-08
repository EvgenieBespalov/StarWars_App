package com.example.starwars_app.data.api

import com.example.starwars_app.data.model.PlanetApiModel
import com.example.starwars_app.data.model.PlanetsListApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetApi {
    @GET("/api/starships/?search={name}&page={page}")
    suspend fun searchPlanet(@Path("name") name: String, @Path("page") page: String): Response<PlanetsListApiModel>//PlanetsListApiModel

    @GET("/api/planets/{id}")
    suspend fun getPlanetById(@Path("id") id: String): PlanetApiModel
}