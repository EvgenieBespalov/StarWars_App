package com.example.starwars_app

import android.app.Application
import com.example.starwars_app.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                provideNetworkModule(),
                provideDataModule(),
                provideDomainModule(),
                providePresentationModule(),
                provideDataBaseModule()
            )
        }
    }
}