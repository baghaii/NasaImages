package com.example.nasaimages.networking

import com.example.nasaimages.data.NasaImagesWrapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaFetcherService {
    @GET("search")
    suspend fun getImageData(
        @Query("media_type") type: String = "image",
        @Query("q") searchTerm: String
    ): NasaImagesWrapper
}