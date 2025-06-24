package com.example.checkbin.di

import android.content.Context
import com.example.checkbin.data.local.dao.BinDataHistoryDao
import com.example.checkbin.data.local.database.BinDataHistoryDatabase
import com.example.checkbin.data.remote.api.BinDataApi
import com.example.checkbin.data.repository.BinDataHistoryRepositoryImpl
import com.example.checkbin.data.repository.BinDataRepositoryImpl
import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.domain.interfaces.repository.BinDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://lookup.binlist.net"

@Module
@InstallIn(SingletonComponent::class)
object Module {

    // Binlist server start

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    // Binlist server end

    @Provides
    fun provideBinDataApi(retrofit: Retrofit): BinDataApi {
        return retrofit.create(BinDataApi::class.java)
    }

    @Provides
    fun provideBinDataDatabase(@ApplicationContext context: Context): BinDataHistoryDatabase {
        return BinDataHistoryDatabase.getDatabase(context)
    }

    @Provides
    fun provideBinDataHistoryDao(dataDatabase: BinDataHistoryDatabase): BinDataHistoryDao {
        return dataDatabase.binDataDao()
    }

    @Provides
    fun provideBinDataRepository(
        binDataApi: BinDataApi
    ): BinDataRepository {
        return BinDataRepositoryImpl(binDataApi = binDataApi)
    }

    @Provides
    fun provideBinDataHistoryRepository(binDataHistoryDao: BinDataHistoryDao): BinDataHistoryRepository {
        return BinDataHistoryRepositoryImpl(binDataHistoryDao)
    }
}