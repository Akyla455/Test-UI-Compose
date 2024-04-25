package com.example.testuigames.network

import com.example.testuigames.model.ListCharacters
import retrofit2.http.GET

interface CharactersService {
    @GET("character")
   suspend fun getCharactersList(): ListCharacters
}
