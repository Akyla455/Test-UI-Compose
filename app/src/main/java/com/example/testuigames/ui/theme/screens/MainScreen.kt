package com.example.testuigames.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.testuigames.bottom_navigation.BottomNavigation
import com.example.testuigames.bottom_navigation.Navigation
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController)
        }
    ) {
        Box(Modifier.padding(it)){
            Navigation(navHostController = navController)
        }
    }

}