package com.example.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.timer.timer
import com.example.compose.ui.theme.ComposeTheme
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TimerScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    ComposeTheme{
        TimerScreen()
    }
}

@Composable
fun MovieList(movies: List<Movie>){


    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        items(movies){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                ,
                colors = CardDefaults.cardColors(containerColor = it.second)
            ){
                Text(
                    text = it.first,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

}

@Composable
private fun TimerScreen(){
    var currentDuration by remember { mutableStateOf(1.minutes) }

    LaunchedEffect(Unit) {
        timer(1.minutes).collect{
            currentDuration = it
        }
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = currentDuration.toTimerString(),
            fontSize = 24.sp
            )
    }
}


typealias Movie = Pair<String, Color>


@SuppressLint("SimpleDateFormat")
fun Duration.toTimerString() = SimpleDateFormat("mm:ss").format(Date(this.inWholeMilliseconds))
