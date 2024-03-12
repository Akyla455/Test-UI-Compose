package com.example.testuigames.ViewModel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testuigames.R

sealed class GameState {
    data object InputRequest : GameState()
    data class Win(@StringRes val titleResource: Int) : GameState()
    data class Game(@StringRes val hintResource: Int, val attempts: Int) : GameState()

}


class GameViewModel : ViewModel() {

    private var random = randomNumbers()
    private var attempts = 0

    private val _gameState = MutableLiveData<GameState>()
    val gameState: LiveData<GameState>
        get() = _gameState

    init {
        startNewGame()
    }

    private fun startNewGame() {
        random = randomNumbers()
        attempts = 0
        _gameState.value = GameState.InputRequest
    }

    fun checkUserInput(userInput: Int) {
        attempts++
        if (userInput == random) {
            _gameState.value = GameState.Win(R.string.request)

        } else if (userInput > random) {
            _gameState.value = GameState.Game(R.string.hint1, attempts)

        } else {
            _gameState.value = GameState.Game(R.string.hint2, attempts)
        }


    }

    fun restartGame() {
        startNewGame()
    }

    private fun randomNumbers(): Int {
        val min = 1
        val max = 10
        return (min..max).random()
    }


}