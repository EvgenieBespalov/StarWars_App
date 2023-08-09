package com.example.starwars_app.data.model

import androidx.room.Entity

@Entity(tableName = "star_wars_table")
data class DatabaseModel(
    val id: String,
    val name: String,
    val image: String,
    val type: String
)
