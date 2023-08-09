package com.example.starwars_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starwars_app.data.dao.DatabaseDAO
import com.example.starwars_app.data.model.DatabaseModel

@Database(entities = [DatabaseModel::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun databaseDAO() : DatabaseDAO
}