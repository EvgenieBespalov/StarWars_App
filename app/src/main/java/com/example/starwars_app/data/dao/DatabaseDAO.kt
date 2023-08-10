package com.example.starwars_app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.starwars_app.data.model.FavoriteModel

@Dao
interface DatabaseDAO {
    @Insert
    fun save(entities: FavoriteModel)

    @Query("SELECT * FROM star_wars_table")
    fun getAll(): List<FavoriteModel>

    @Query("SELECT * FROM star_wars_table WHERE id = :id AND type = :type LIMIT 1")
    fun checkSave(id: String, type: String): FavoriteModel
}