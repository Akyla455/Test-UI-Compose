package com.example.testuigames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testuigames.ui.theme.TestUIGamesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameScreen()

        }
    }
}

@Composable
fun GameScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TitleText(text = "Угадай число", textSize = 20.sp)
        TitleText(text ="Я думаю о числе от 1 до 10. Сможешь угадать?" ,
            textSize = 26.sp )
        InputField()
        CheckButton("Ввод", 20.sp)

    }

}

@Composable
fun TitleText(modifier: Modifier = Modifier, text: String, textSize: TextUnit){
    Row(modifier = Modifier.padding(vertical = 30.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = text,
            modifier = modifier,
            style = TextStyle(fontSize = textSize)
        )
    }
}

@Composable
fun CheckButton(buttonText: String, buttonTextSize: TextUnit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        horizontalArrangement = Arrangement.Center){
        Button(onClick = { /*TODO*/ },
            modifier = Modifier.size(width = 400.dp, height = 40.dp)) {
            Text(text = buttonText,
                fontSize = buttonTextSize)
        }
    }
}

@Composable
fun InputField(){


    TextField(value = "", onValueChange = {} )
}

@Preview
@Composable
fun Preview(){
    TestUIGamesTheme {
        CheckButton("Ввод", 20.sp)
    }
}

@Preview
@Composable
fun PreviewGameScreen(){
    TestUIGamesTheme {
        GameScreen()
    }
}