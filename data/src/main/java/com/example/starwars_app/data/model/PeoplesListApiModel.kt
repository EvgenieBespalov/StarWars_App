package com.example.starwars_app.data.model

import com.google.gson.annotations.SerializedName

data class PeoplesListApiModel(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<PeopleApiModel>,
)
