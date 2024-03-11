package com.example.testuigames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.testuigames.ui.theme.TestUIGamesTheme
import org.w3c.dom.Text

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
    Column(modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(fontSize = 16.sp,
            style = TextStyle(color = Color.Black),
            text = stringResource(R.string.title))
        TitleText(stringResource = R.string.condition)
        ProcessingUserInput()

    }


}

@Composable
fun TitleText(stringResource: Int){
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 25.dp),
        style = TextStyle(color = Color.Black),
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        text = stringResource(stringResource))
}

@Composable
fun ProcessingUserInput() {
    val userInput = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = userInput.value , onValueChange = {newInput ->
            userInput.value = newInput
        }, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp) )
        Button(onClick = {


        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
                .height(50.dp)
                ) {
            Text(text = stringResource(R.string.input),
                style = TextStyle(color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold),



            )

        }

    }


}

@Preview
@Composable
fun PreviewProcessingUserInput(){
    TestUIGamesTheme {
        ProcessingUserInput()
    }
}

@Preview
@Composable
fun PreviewGameScree(){
    TestUIGamesTheme {
        GameScreen()
    }
}
