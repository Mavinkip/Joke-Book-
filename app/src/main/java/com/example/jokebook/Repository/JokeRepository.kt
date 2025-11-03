package com.example.jokebook.Repository

import com.example.jokebook.DataModel.JokeResponse
import com.example.jokebook.DomainModel.Joke
import com.example.jokebook.RetrofitInstance.RetrofitClient



class JokeRepository {
    private val jokeApi=RetrofitClient.jokeApi


    suspend fun getRandomJoke(): Result<Joke>{
        return try{
            val response = jokeApi.getRandomJoke()
            if(!response.error){
                Result.success(response.toJoke())
            }else{
                Result.failure(Exception("failed to fetch joke"))
            }

        }catch(e: Exception){
            Result.failure(e)
        }

    }

    suspend fun getJokeByCategory(category:String): Result<Joke>{
        return try{
            val response = jokeApi.getJokeByCategory(category)
            if(!response.error){
                Result.success(response.toJoke())
            }else {
                Result.failure(Exception("Failed to fetch joke"))
            }
            }catch(e: Exception){
                Result.failure(e)
            }

        }
    }
private fun JokeResponse.toJoke(): Joke {
    return Joke(
        id = id.toString(),
        setup = setup ?: joke ?: "No joke content available",
        punchLine = delivery ?: "",
        category = category,
        isTwoPart = type == "twopart"
    )
}