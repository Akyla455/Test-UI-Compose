package com.example.testuigames.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.testuigames.bottom_navigation.BottomNavigation
import com.example.testuigames.bottom_navigation.Navigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController)
        }
    ) {
        Navigation(navHostController = navController)
    }

}