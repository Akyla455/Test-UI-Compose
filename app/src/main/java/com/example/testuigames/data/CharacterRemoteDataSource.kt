package com.example.testuigames.data

import com.example.testuigames.network.CharacterRetrofit
import com.example.testuigames.network.CharactersService

class CharacterRemoteDataSource(
    characterRetrofit: CharacterRetrofit
) {
    val charactersService: CharactersService by lazy{
        characterRetrofit.retrofit.create(CharactersService::class.java)
    }
}