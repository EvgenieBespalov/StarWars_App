package com.example.starwars_app.data.api

import com.example.starwars_app.data.model.StarshipApiModel
import com.example.starwars_app.data.model.StarshipsListApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarshipApi{
    @GET("/api/starships/")
    suspend fun searchPlanet(@Query("search") name: String, @Query("page") page: String): Response<StarshipsListApiModel>

    @GET("/api/starships/{id}/")
    suspend fun getPlanetById(@Path("id") id: String): StarshipApiModel
}