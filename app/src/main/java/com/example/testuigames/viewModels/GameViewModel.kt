package com.example.testuigames.viewModels

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testuigames.R
import com.example.testuigames.data.DataBasePreferences
import com.example.testuigames.network.NetworkManager
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

class GameViewModel : ViewModel() {
    private val dataBase = DataBasePreferences()
    private var random = randomNumbers()
    private var attempts = 0
    private var maxCurrencyValue: Int = 0
    private val appId = "686b5827047e4e33bdc06effd904e56d"
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
                val call = NetworkManager.currencyApi.getCurrencyData(appId)

                try {
                    val response = call.execute()
                    if (response.isSuccessful) {
                        val dataCurrency = response.body()
                        if (dataCurrency != null) {
                            //dataBase.deleteData()
                            maxCurrencyValue = dataCurrency.rates.rub.toInt()
                            //dataBase.saveData(maxCurrencyValue)
                            startNewGame(maxCurrencyValue)
                        }
                    } else {
                        //maxCurrencyValue = dataBase.getData()
                        Log.e("GameViewModel", "Error: ${response.code()}")
                        startNewGame()
                    }
                } catch (e: Exception) {
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
        val max = 10

        return if (maxCurrencyValue > 0) {
            (min..maxCurrencyValue).random()
        } else (min..max).random()

    }


}
