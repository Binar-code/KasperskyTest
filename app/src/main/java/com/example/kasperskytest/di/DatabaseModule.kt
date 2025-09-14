package com.example.kasperskytest.di

import androidx.room.Room
import com.example.data.local.AppDatabase
import com.example.data.local.dao.WordDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "database.db"
        ).build()
    }

    single<WordDao> {
        get<AppDatabase>().wordDao()
    }
}