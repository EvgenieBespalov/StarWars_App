package com.example.starwars_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starwars_app.data.dao.DatabaseDAO
import com.example.starwars_app.data.model.FavoriteModel

@Database(entities = [FavoriteModel::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract fun databaseDAO() : DatabaseDAO
}