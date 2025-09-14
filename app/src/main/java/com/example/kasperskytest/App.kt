package com.example.kasperskytest

import android.app.Application
import com.example.kasperskytest.di.dbModule
import com.example.kasperskytest.di.networkModule
import com.example.kasperskytest.di.repoModule
import com.example.kasperskytest.di.useCaseModule
import com.example.kasperskytest.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(repoModule, networkModule, dbModule, useCaseModule, vmModule)
        }
    }
}

