package com.example.checkbin.di

import com.example.checkbin.data.local.dao.BinDataHistoryDao
import com.example.checkbin.data.local.database.BinDataHistoryDatabase
import com.example.checkbin.data.remote.api.BinDataApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleMock {

    @Singleton
    @Provides
    fun provideBinDataApi(): BinDataApi = mockk()

    @Singleton
    @Provides
    fun provideBinDataDatabase(): BinDataHistoryDatabase = mockk()

    @Singleton
    @Provides
    fun provideBinDataHistoryDao(): BinDataHistoryDao = mockk()

    @Singleton
    @Provides
    @IoDispatcher
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.Main
}