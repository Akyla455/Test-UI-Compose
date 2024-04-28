package com.example.testuigames.data

import com.example.testuigames.network.CharactersService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface AppContainer {
    val charactersRepository: CharactersRepository
}

class DefaultAppContainer: AppContainer{
    private val BASE_URL = "https://rickandmortyapi.com/api/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    private val retrofitService: CharactersService by lazy {
        retrofit.create(CharactersService::class.java)
    }
    override val charactersRepository: CharactersRepository by lazy {
        NetworkCharactersRepository(retrofitService)
    }

}