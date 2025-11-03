package com.example.jokebook.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokebook.DomainModel.Joke
import com.example.jokebook.Repository.JokeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JokeViewModel: ViewModel() {
    private val repository = JokeRepository()
     private val _jokeState= MutableStateFlow<JokeState>(JokeState.Loading)
    val jokeState: StateFlow<JokeState> =_jokeState

    fun getRandomJoke() {
        viewModelScope.launch {
            _jokeState.value = JokeState.Loading
            val result = repository.getRandomJoke()
            if (result.isSuccess) {
                _jokeState.value = JokeState.Success(result.getOrNull()!!)
            } else {
                _jokeState.value = JokeState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
    fun getJokeByCategory(category:String){
        viewModelScope.launch {
            _jokeState.value = JokeState.Loading
            val result = repository.getJokeByCategory(category)
            if(result.isSuccess){

                _jokeState.value = JokeState.Success(result.getOrNull()!!)

            }
            else {
                _jokeState.value = JokeState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

            sealed class JokeState{
                object Loading : JokeState()
                data class Success(val joke: Joke):JokeState()
                data class Error(val message: String): JokeState()
            }


}