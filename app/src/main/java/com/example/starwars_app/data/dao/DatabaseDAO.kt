package com.example.starwars_app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.starwars_app.data.model.DatabaseModel

@Dao
interface DatabaseDAO {
    @Insert
    fun save(entities: DatabaseModel)

    @Query("SELECT * FROM star_wars_table")
    fun getAll(): List<DatabaseModel>

    @Query("SELECT * FROM star_wars_table WHERE id = :id AND type = :type LIMIT 1")
    fun checkSave(id: String, type: String): DatabaseModel
}