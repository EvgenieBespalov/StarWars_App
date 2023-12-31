package com.example.starwars_app.di

import android.service.controls.ControlsProviderService
import android.util.Log
import com.example.starwars_app.data.api.PeopleApi
import com.example.starwars_app.data.api.PlanetApi
import com.example.starwars_app.data.api.StarshipApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException
import java.util.concurrent.TimeUnit


private const val BASE_URL = "https://swapi.dev/"
private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 10L
private const val READ_TIMEOUT = 10L

private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient().newBuilder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(ReceivedCookiesInterceptor())
        .build()

private fun provideGson(): Gson =
    GsonBuilder()
        .create()


private fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

private fun providePlanetApi(retrofit: Retrofit): PlanetApi =
    retrofit.create()

private fun providePeopleApi(retrofit: Retrofit): PeopleApi =
    retrofit.create()

private fun provideStarshipApi(retrofit: Retrofit): StarshipApi =
    retrofit.create()

class ReceivedCookiesInterceptor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response
    {
        val request = chain.request()
        var response = chain.proceed(request)

        Log.i(ControlsProviderService.TAG, "request: "+ request.toString())
        Log.i(ControlsProviderService.TAG, "response: "+ response.toString())

        return response
    }
}

fun provideNetworkModule(): Module =
    module {
        single { provideOkHttpClient() }
        single { provideGson() }
        single { provideRetrofit(okHttpClient = get(), gson = get()) }
        single { providePlanetApi(retrofit = get()) }
        single { providePeopleApi(retrofit = get()) }
        single { provideStarshipApi(retrofit = get()) }
    }