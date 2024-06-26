package com.example.testuigames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.testuigames.ui.theme.screens.MainScreen
import com.example.testuigames.ui.theme.TestUIGamesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestUIGamesTheme {
              MainScreen()
            }

        }
    }
}




