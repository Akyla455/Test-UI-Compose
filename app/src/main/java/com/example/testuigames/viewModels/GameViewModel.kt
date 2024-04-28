package com.example.testuigames.viewModels

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testuigames.R
import com.example.testuigames.data.CurrencyRepository
import com.example.testuigames.data.DataBasePreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



sealed class GameState {
    data object LoadingState : GameState()
    data class Win(
        @StringRes val titleResource: Int
    ) : GameState()
    data class Game(
        @StringRes val titleResource: Int,
        @StringRes val hintResource: Int? = null,
        val attempts: Int,
        val maxvalue: Int
    ) : GameState()

}

class GameViewModel(
    private val dataBasePreferences: DataBasePreferences,
    private val currencyRepository: CurrencyRepository
) : ViewModel() {
    private var maxCurrencyValue: Int = 10
    private var random = randomNumbers()
    private var attempts = 0
    private val _gameState = MutableLiveData<GameState>()
    val gameState: LiveData<GameState>
        get() = _gameState



    init {
        fetchCurrencyData()

    }

    private fun startNewGame(currencyValue: Int? = null) {
        if (currencyValue != null) {
            maxCurrencyValue = currencyValue
        }
        random = randomNumbers()
        attempts = 0
        _gameState.postValue(
            GameState.Game(
                R.string.input_request,
                null,
                attempts,
                maxCurrencyValue
            )
        )
    }
    private fun fetchCurrencyData() {
        _gameState.value = GameState.LoadingState
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val call = currencyRepository.getCurrency()

                try {
                    val response = call.execute()
                    if (response.isSuccessful) {
                        val dataCurrency = response.body()
                        if (dataCurrency != null) {
                            dataBasePreferences.deleteData()
                            maxCurrencyValue = dataCurrency.rates.rub.toInt()
                            dataBasePreferences.saveData(maxCurrencyValue)
                            startNewGame(maxCurrencyValue)
                        }
                    } else {
                        Log.e("GameViewModel", "Error: ${response.code()}")
                    }
                } catch (e: Exception) {
                    maxCurrencyValue = dataBasePreferences.getData()
                    startNewGame(maxCurrencyValue)
                    Log.e("GameViewModel", "Failed to fetch currency data", e)
                }
            }
        }
    }
    fun checkUserInput(userInput: Int) {
        attempts++
        if (userInput == random) {
            _gameState.value = GameState.Win(R.string.request)

        } else if (userInput > random) {
            _gameState.value = GameState.Game(
                R.string.input_request,
                R.string.hint1,
                attempts,
                maxCurrencyValue
            )

        } else {
            _gameState.value = GameState.Game(
                R.string.input_request,
                R.string.hint2,
                attempts,
                maxCurrencyValue
            )
        }


    }
    fun restartGame() {
        startNewGame()
    }
    private fun randomNumbers(): Int {
        val min = 1
        return (min..maxCurrencyValue).random()
    }
}
