package com.example.testuigames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testuigames.viewModel.GameState
import com.example.testuigames.viewModel.GameViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameScreenState()

        }
    }
}


@Composable
fun GameScreenState(viewModel: GameViewModel = viewModel()) {
    val gameState by viewModel.gameState.observeAsState()
    gameState?.let { state ->
        when (state) {
            is GameState.InputRequest -> {
                Column {
                    GameScreen(
                        resource = null,
                        attempts = 0,
                        viewModel
                    )

                }

            }

            is GameState.Win -> {
                GameScreenRestart(
                    onRestartGameTap = { viewModel.restartGame() },
                    stringResource = state.titleResource
                )
            }

            is GameState.Game -> {
                Column {
                    GameScreen(
                        resource = state.hintResource,
                        attempts = state.attempts,
                        viewModel
                    )

                }
            }

        }

    }


}

@Composable
fun GameScreen(
    resource: Int?,
    attempts: Int,
    viewModel: GameViewModel
) {


    Column(
        modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            fontSize = 16.sp,
            style = TextStyle(color = Color.Black),
            text = stringResource(R.string.title)
        )
        TitleText(stringResource = R.string.input_request)
        ProcessingUserInput(viewModel)
        HintText(hintResource = resource)
        NumberAttempts(attempts = attempts)


    }


}


@Composable
fun TitleText(stringResource: Int) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        style = TextStyle(color = Color.Black),
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        text = stringResource(stringResource)
    )
}

@Composable
fun ProcessingUserInput(viewModel: GameViewModel) {

    val userInput = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = userInput.value,
            onValueChange = { newInput ->
                userInput.value = newInput
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

        )
        Button(
            onClick = {
                userInput.value.toIntOrNull()?.let { number ->
                    viewModel.checkUserInput(number)
                    userInput.value = ""
                }


            }, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(R.string.input),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                ),


                )

        }

    }
}

@Composable
fun HintText(hintResource: Int?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            fontSize = 20.sp,
            style = TextStyle(color = Color.Black),
            text = stringResource(R.string.hint),
            fontWeight = FontWeight.Bold
        )
        hintResource?.let {
            Text(
                stringResource(hintResource),
                fontSize = 18.sp,
                style = TextStyle(color = Color.Black)
            )
        }


    }

}

@Composable
fun NumberAttempts(attempts: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.attempts),
            fontSize = 20.sp,
            style = TextStyle(color = Color.Black),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = attempts.toString(), fontSize = 20.sp, style = TextStyle(color = Color.Black)
        )
    }
}

class ActivityProvider(private val activity: ComponentActivity?) {
    fun finishCurrentActivity() {
        activity?.finish()
    }
}


@Composable
fun GameScreenRestart(
    onRestartGameTap: () -> Unit, stringResource: Int
) {

    val context = LocalContext.current as? ComponentActivity
    val activityProvider = remember {
        ActivityProvider(context)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 100.dp, horizontal = 20.dp)
    ) {
        TitleText(stringResource = stringResource)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = onRestartGameTap,
                modifier = Modifier.size(width = 100.dp, height = 50.dp)
            ) {
                Text(
                    stringResource(R.string.yes),
                    fontSize = 18.sp,
                    style = TextStyle(color = Color.White),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Button(
                onClick = { activityProvider.finishCurrentActivity() },
                modifier = Modifier.size(width = 100.dp, height = 50.dp)
            ) {
                Text(
                    stringResource(R.string.no),
                    fontSize = 18.sp,
                    style = TextStyle(color = Color.White),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}

