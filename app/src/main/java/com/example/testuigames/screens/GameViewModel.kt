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
    data object LoadingState : GameState()
    data class Win(@StringRes val titleResource: Int) : GameState()
    data class Game(
        @StringRes val titleResource: Int,
        @StringRes val hintResource: Int? = null,
        val attempts: Int,
        val maxvalue: Int
    ) : GameState()

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

    }

    private fun startNewGame(currencyValue: Int? = null) {
        if (currencyValue != null) {
            maxCurrencyValue = currencyValue
        }
        random = randomNumbers()
        attempts = 0
        _gameState.postValue(
            GameState.Game(
                R.string.input_request, null, attempts, maxCurrencyValue
            )
        )
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
                            maxCurrencyValue = dataCurrency.rates.rub.toInt()
                            startNewGame(maxCurrencyValue)
                        }
                    } else {
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
                R.string.input_request, R.string.hint1, attempts, maxCurrencyValue
            )

        } else {
            _gameState.value = GameState.Game(
                R.string.input_request, R.string.hint2, attempts, maxCurrencyValue
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
