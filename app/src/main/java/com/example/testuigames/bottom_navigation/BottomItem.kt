package com.example.testuigames.bottom_navigation

import com.example.testuigames.R

sealed class BottomItem(
    val text: String,
    val icon: Int,
    val route: String
) {
    data object Game : BottomItem(
        "Game",
        R.drawable.game,
        ConsDataNavigation.GAME_ROUTE
    )

    data object Characters : BottomItem(
        "characters",
        R.drawable.characters,
        ConsDataNavigation.CHARACTERS_ROUTE
    )
}