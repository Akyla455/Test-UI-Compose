package com.example.testuigames.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testuigames.data.CharactersRepository
import com.example.testuigames.model.InfoCharacters
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface InfoState {
    data object Error : InfoState
    data object Loading : InfoState
    data class Success(val infoCharacter: List<InfoCharacters>) : InfoState
}

class InfoCharactersViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {
    var infoState: InfoState by mutableStateOf(InfoState.Loading)
        private set

    init {
        fetchInfoCharacter()
    }

    private fun fetchInfoCharacter() {
        viewModelScope.launch {
            infoState = InfoState.Loading
            infoState =
                try {
                    InfoState.Success(charactersRepository.getCharacters())
                } catch (e: IOException) {
                    InfoState.Error
                } catch (e: HttpException) {
                    InfoState.Error
                }
        }
    }
}