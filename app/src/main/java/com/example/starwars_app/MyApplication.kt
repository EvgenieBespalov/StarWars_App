package com.example.starwars_app

import android.app.Application
import com.example.starwars_app.di.provideDataModule
import com.example.starwars_app.di.provideDomainModule
import com.example.starwars_app.di.provideNetworkModule
import com.example.starwars_app.di.providePresentationModule
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
            )
        }
    }
}