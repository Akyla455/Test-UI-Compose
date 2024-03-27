package com.example.testuigames.screens

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testuigames.R
import com.example.testuigames.network.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class GameState {
    data object LoadingState: GameState()
    data object InputRequest : GameState()
    data class Win(@StringRes val titleResource: Int) : GameState()
    data class Game(@StringRes val hintResource: Int, val attempts: Int) : GameState()

}


class GameViewModel() : ViewModel(), Parcelable {

    private var random = randomNumbers()
    private var attempts = 0
    private var maxCurrencyValue: Int = 0

    private val _gameState = MutableLiveData<GameState>()
    val gameState: LiveData<GameState>
        get() = _gameState

    constructor(parcel: Parcel) : this() {
        random = parcel.readInt()
        attempts = parcel.readInt()
    }

    init {
        fetchCurrencyData()
        //startNewGame()
    }

    private fun startNewGame(startNumber: Int? = null) {
        random = startNumber ?: randomNumbers()
        attempts = 0
        _gameState.postValue(GameState.InputRequest)
    }

    private fun fetchCurrencyData() {
        _gameState.value = GameState.LoadingState
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val call = NetworkManager.currencyApi.getCurrencyData()

                try {
                    val response = call.execute()
                    if (response.isSuccessful) {
                        val dataCurrency = response.body()
                        if (dataCurrency != null) {
                            maxCurrencyValue = dataCurrency.rates.afn.toInt()
                            startNewGame(maxCurrencyValue)
                        }
                    } else Log.e("GameViewModel", "Error: ${response.code()}")
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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(random)
        parcel.writeInt(attempts)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameViewModel> {
        override fun createFromParcel(parcel: Parcel): GameViewModel {
            return GameViewModel(parcel)
        }

        override fun newArray(size: Int): Array<GameViewModel?> {
            return arrayOfNulls(size)
        }
    }
}
