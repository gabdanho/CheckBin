package com.example.checkbin.di

import com.example.checkbin.data.local.dao.BinDataHistoryDao
import com.example.checkbin.data.local.database.BinDataHistoryDatabase
import com.example.checkbin.data.remote.api.BinDataApi
import com.example.checkbin.data.repository.BinDataHistoryRepositoryImpl
import com.example.checkbin.data.repository.BinDataRepositoryImpl
import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.domain.interfaces.repository.BinDataRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    // Ktor HttpClient
    single {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                gson()
            }
            install(Logging) {
                level = LogLevel.BODY
            }
        }
    }

    // BinDataApi
    single {
        BinDataApi(get())
    }

    // BinDataHistoryDatabase
    single {
        BinDataHistoryDatabase.getDatabase(androidContext())
    }

    // BinDataHistoryDao
    single {
        get<BinDataHistoryDatabase>().binDataDao()
    }

    // BinDataRepository
    single<BinDataRepository> {
        BinDataRepositoryImpl(get<BinDataApi>())
    }

    // BinDataHistoryRepository
    single<BinDataHistoryRepository> {
        BinDataHistoryRepositoryImpl(get<BinDataHistoryDao>())
    }

    // CoroutineDispatcher
    single {
        Dispatchers.IO
    }
}