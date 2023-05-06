package com.example.nasaimages.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaFetcherService {
    companion object {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://images-api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NasaFetcherService::class.java)
    }

    @GET("search")
    suspend fun getImageData(
        @Query("media_type") type: String = "image",
        @Query("q") searchTerm: String
    ): NasaImagesWrapper
}