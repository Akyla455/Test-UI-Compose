package com.example.testuigames.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testuigames.ui.theme.screens.CharactersApp
import com.example.testuigames.ui.theme.screens.GameScreenState

@Composable
fun Navigation(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "characters_screen") {
        composable("game_screen") {
            GameScreenState()
        }
        composable("characters_screen") {
            CharactersApp()
        }
    }
}