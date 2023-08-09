package com.example.starwars_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "star_wars_table")
data class DatabaseModel(
    val id: String,
    val name: String,
    val image: String,
    val type: String
){
    @PrimaryKey(autoGenerate = true)
    var key: Int = 0
}
