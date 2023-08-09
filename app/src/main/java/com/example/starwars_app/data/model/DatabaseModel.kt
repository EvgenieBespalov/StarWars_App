package com.example.starwars_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "star_wars_table", primaryKeys = ["id", "type"])
data class DatabaseModel(
    val id: String,
    val type: String,
    val name: String,
    val image: String,
)