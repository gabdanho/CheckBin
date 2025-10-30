package com.example.checkbin.app

import android.app.Application
import com.example.checkbin.di.appModule
import com.example.checkbin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CheckBinApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CheckBinApp)
            modules(appModule, viewModelModule)
        }
    }
}