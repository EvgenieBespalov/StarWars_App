package com.example.starwars_app.data.model

import androidx.room.Entity

@Entity(tableName = "star_wars_table", primaryKeys = ["id", "type"])
data class FavoriteModel(
    val id: String,
    val type: String,
    val name: String,
    val image: String,
)