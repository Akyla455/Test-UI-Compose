package com.example.testuigames.bottom_navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.testuigames.ui.theme.screens.CharactersApp
import com.example.testuigames.ui.theme.screens.GameScreenState
import com.example.testuigames.ui.theme.screens.InfoCharactersScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = ConsDataNavigation.CHARACTERS_ROUTE
    ) {
        composable(ConsDataNavigation.GAME_ROUTE) {
            GameScreenState()
        }
        composable(ConsDataNavigation.CHARACTERS_ROUTE) {
            CharactersApp(navHostController)
        }
        composable(
            "${
                ConsDataNavigation
                    .INFO_CHARACTERS_SCREEN
            }/{infoCharactersId}",
            arguments = listOf(navArgument("infoCharactersId") {
                type = NavType.IntType
            })
        )
        { backStackEntry ->
            val infoCharactersId = backStackEntry.arguments?.getInt("infoCharactersId")
            InfoCharactersScreen(
                infoCharactersId = infoCharactersId
            )
        }
    }
}
