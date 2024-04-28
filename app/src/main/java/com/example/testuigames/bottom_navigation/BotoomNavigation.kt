package com.example.testuigames.bottom_navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavigation(
    navController: NavController
) {
    val items = listOf(
        BottomItem.Game,
        BottomItem.Characters
    )

    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        painterResource(item.icon),
                        contentDescription = "Icon"
                    )
                },
                label = {
                    Text(
                        text = item.text,
                        fontSize = 14.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors()
            )

        }
    }
}


