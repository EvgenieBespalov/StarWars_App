package com.example.starwars_app.di

import androidx.room.Room
import com.example.starwars_app.data.database.Database
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideDataBaseModule(): Module =
    module{
        single {
            Room.databaseBuilder(androidApplication(), Database::class.java, "bin_db")
                .fallbackToDestructiveMigration()
                .build()

        }

        single { get<Database>().databaseDAO() }
    }