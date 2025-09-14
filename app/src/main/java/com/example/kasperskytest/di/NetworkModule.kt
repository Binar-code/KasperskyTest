package com.example.kasperskytest.di

import com.example.data.network.api.SkyengApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder().build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://dictionary.skyeng.ru/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<SkyengApi> { get<Retrofit>().create(SkyengApi::class.java) }
}