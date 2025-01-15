package com.example.checkbin.di

import android.content.Context
import com.example.checkbin.data.local.BinDataDao
import com.example.checkbin.data.local.BinDataDatabase
import com.example.checkbin.data.remote.BinlistApi
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

    @Provides
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideBinlistApi(retrofit: Retrofit): BinlistApi {
        return retrofit.create(BinlistApi::class.java)
    }

    @Provides
    fun provideBinDataDatabase(@ApplicationContext context: Context): BinDataDatabase {
        return BinDataDatabase.getDatabase(context)
    }

    @Provides
    fun provideBinDataDao(dataDatabase: BinDataDatabase): BinDataDao {
        return dataDatabase.binDataDao()
    }
}