package com.example.starwars_app.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.starwars_app.data.model.DatabaseModel

interface DatabaseDAO {
    @Insert
    fun save(entities: DatabaseModel)

    @Query("SELECT * FROM database")
    fun getAll(): List<DatabaseModel>
}