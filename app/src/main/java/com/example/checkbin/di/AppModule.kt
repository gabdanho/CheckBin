package com.example.checkbin.di

import com.example.checkbin.data.local.dao.BinDataHistoryDao
import com.example.checkbin.data.local.database.BinDataHistoryDatabase
import com.example.checkbin.data.remote.api.BinDataApi
import com.example.checkbin.data.repository.BinDataHistoryRepositoryImpl
import com.example.checkbin.data.repository.BinDataRepositoryImpl
import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.domain.interfaces.repository.BinDataRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://lookup.binlist.net"

val appModule = module {

    // HttpLoggingInterceptor
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // OkHttpClient
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    // BinDataApi
    single {
        get<Retrofit>().create(BinDataApi::class.java)
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