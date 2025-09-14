package com.example.kasperskytest.di

import com.example.data.local.repository.LocalStorageRepositoryImpl
import com.example.data.network.repository.NetworkRepositoryImpl
import com.example.domain.repository.LocalStorageRepository
import com.example.domain.repository.NetworkRepository
import org.koin.dsl.module

val repoModule = module {
    single<NetworkRepository> {
        NetworkRepositoryImpl(get())
    }

    single<LocalStorageRepository> {
        LocalStorageRepositoryImpl(get())
    }
}