package com.example.testuigames.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testuigames.ui.theme.screens.CharactersApp
import com.example.testuigames.ui.theme.screens.GameScreenState
import com.example.testuigames.ui.theme.screens.InfoCharactersScreen

@Composable
fun Navigation(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = ConsDataNavigation.CHARACTERS_ROUTE) {
        composable(ConsDataNavigation.GAME_ROUTE) {
            GameScreenState()
        }
        composable(ConsDataNavigation.CHARACTERS_ROUTE) {
            CharactersApp()
        }
//        composable(ConsDataNavigation.INFO_CHARACTERS_SCREEN){
//            InfoCharactersScreen(info = )
//        }
    }
}