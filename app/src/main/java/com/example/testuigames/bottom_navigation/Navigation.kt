package com.example.testuigames.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testuigames.data.CharactersRepository
import com.example.testuigames.data.NetworkCharactersRepository
import com.example.testuigames.ui.theme.screens.CharactersApp
import com.example.testuigames.ui.theme.screens.GameScreenState
import com.example.testuigames.ui.theme.screens.InfoCharactersScreen
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getKoin

@Composable
fun Navigation(
    navHostController: NavHostController,
    charactersRepository: CharactersRepository = get()
) {
    NavHost(navController = navHostController, startDestination = ConsDataNavigation.CHARACTERS_ROUTE) {
        composable(ConsDataNavigation.GAME_ROUTE) {
            GameScreenState()
        }
        composable(ConsDataNavigation.CHARACTERS_ROUTE) {
            CharactersApp(navHostController)
        }
        composable("${ConsDataNavigation.INFO_CHARACTERS_SCREEN}/{infoCharactersId}"){ backStackEntry ->
            val infoCharactersId = backStackEntry.arguments?.getInt("infoCharactersId")
            infoCharactersId.let { id ->
                InfoCharactersScreen(
                    infoCharactersId = infoCharactersId,
                    charactersRepository
                )
            }
            }
        }
    }
