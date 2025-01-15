package com.example.checkbin.data.remote

import com.example.whatbin.model.BinInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BinlistApi {
    @Headers("Accept-Version: 3")
    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): Response<BinInfo>
}