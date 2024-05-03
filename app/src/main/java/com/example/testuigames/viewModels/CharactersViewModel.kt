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

sealed interface CharactersUiState{
    data class Success(val charactersSearch: List<InfoCharacters>) : CharactersUiState
    data object Error: CharactersUiState
    data object Loading: CharactersUiState
}

class CharactersViewModel(
    private val charactersRepository: CharactersRepository
): ViewModel(){

    var charactersUiState: CharactersUiState by mutableStateOf(CharactersUiState.Loading)
        private set


    init {
        fetchCharactersData()
    }

    fun fetchCharactersData(){
        viewModelScope.launch {
            charactersUiState = CharactersUiState.Loading
            charactersUiState =
                try {
                    CharactersUiState.Success(charactersRepository.getCharacters())
                } catch (e: IOException) {
                    CharactersUiState.Error
                } catch (e: HttpException) {
                    CharactersUiState.Error
                }
        }
    }
}





