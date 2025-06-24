package com.example.checkbin.data.remote.api

import com.example.checkbin.data.remote.model.BinDataRequest
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BinDataApi {

    @Headers("Accept-Version: 3")
    @GET("{bin}")
    suspend fun getBinData(@Path("bin") bin: String): BinDataRequest
}