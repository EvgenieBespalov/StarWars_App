package com.example.starwars_app.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.starwars_app.data.model.DatabaseModel

interface DatabaseDAO {
    @Insert
    fun save(entities: DatabaseModel)

    @Query("SELECT * FROM star_wars_table")
    fun getAll(): List<DatabaseModel>
}