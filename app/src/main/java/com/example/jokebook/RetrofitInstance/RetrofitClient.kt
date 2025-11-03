package com.example.jokebook.RetrofitInstance

import com.example.jokebook.APIServiceInterface.JokeApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val loggingInterceptor =HttpLoggingInterceptor().apply{
        level =  HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient= OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val jokeApi: JokeApiService by lazy{
        Retrofit.Builder()
            .baseUrl(JokeApiService.JOKE_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokeApiService::class.java)
    }
}
