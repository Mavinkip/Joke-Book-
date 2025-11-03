package com.example.jokebook

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jokebook.ViewModel.JokeViewModel
import com.example.jokebook.ui.theme.JokeBookTheme

class MainActivity : ComponentActivity() {
    private val viewModel: JokeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokeBookTheme {
                   JokeBookScreen(viewModel=viewModel)
                }
            }
        }
    }
@Composable
fun JokeBookScreen(viewModel: JokeViewModel){
    val jokeState by viewModel.jokeState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment =Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        when(val state = jokeState){
            is JokeViewModel.JokeState.Loading ->{
                CircularProgressIndicator()
                Text("wait for it")
            }
            is JokeViewModel.JokeState.Success ->{
                val joke = state.joke
                if(joke.isTwoPart){
                    Text(
                    text = joke.setup,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center

                    )
                    Spacer(modifier =Modifier.height(16.dp))
                    Text(
                        text = joke.punchLine,
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center

                    )

                } else{
                    Text(
                        text=joke.setup,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center

                    )
                }
            }
            is JokeViewModel.JokeState.Error ->{
                Text(
                    text = "Error: ${state.message}",
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {viewModel.getRandomJoke()}
        ) {
            Text("Another One")
        }
    }
}
