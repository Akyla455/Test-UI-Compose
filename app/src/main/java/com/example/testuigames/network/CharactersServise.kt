package com.example.testuigames.network

import com.example.listcharacters.ListCharacters
import retrofit2.http.GET

interface CharactersService {
    @GET("character")
   suspend fun getCharactersList(): ListCharacters
}
