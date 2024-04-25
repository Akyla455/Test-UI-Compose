package com.example.testuigames.data

import com.example.testuigames.model.InfoCharacters
import com.example.testuigames.network.CharactersService

interface CharactersRepository {
    suspend fun getCharacters(): List<InfoCharacters>
}

class NetworkCharactersRepository(
    private val charactersService: CharactersService
): CharactersRepository{
    override suspend fun getCharacters(): List<InfoCharacters> {
        return charactersService
            .getCharactersList().results.map { results ->
                InfoCharacters(
                    name = results.name,
                    image = results.image,
                    species = results.species,
                    episode = results.episode
                )
            }
    }
}