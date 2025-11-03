package com.example.jokebook.APIServiceInterface

import com.example.jokebook.DataModel.JokeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JokeApiService {

    //jokeAPI - Gets any joke
    @GET("Any")
    suspend fun getRandomJoke(): JokeResponse

    //gets by category

    @GET("{category}")
    suspend fun  getJokeByCategory(@Path("category")category:String):JokeResponse

    companion object{
        const val JOKE_API_BASE_URL="https://v2.jokeapi.dev/joke/"

    }

}