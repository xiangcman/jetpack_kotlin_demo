package com.single.jetpack_demo.net

import com.hankkin.jetpack_note.data.bean.GankResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("data/Android/{count}/{page}")
    fun getGank(
        @Path("count") count: Int,
        @Path("page") page: Int
    ): Call<GankResponse>

    companion object {
        val BASE_URL = "http://gank.io/api/"
        val logger = HttpLoggingInterceptor()
        val instance: ApiService by lazy {
            logger.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}