package com.example.pagoappmvvm.network

import com.example.pagoappmvvm.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceProvider(private val okHttpClient: OkHttpClient) {
    fun createApiService(): ApiService {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = retrofitBuilder.client(okHttpClient).build()
        return retrofit.create(ApiService::class.java)
    }
}