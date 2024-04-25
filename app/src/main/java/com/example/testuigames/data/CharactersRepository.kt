package com.example.testuigames.data

import com.example.testuigames.model.InfoCharacters
import com.example.testuigames.network.CharactersService

interface CharactersRepository {
    suspend fun getCharacters(): List<InfoCharacters>
}

class NetworkCharactersRepository(
    private val charactersService: CharacterRemoteDataSource
): CharactersRepository{
    override suspend fun getCharacters(): List<InfoCharacters> {
        return charactersService.characterService
            .getCharactersList().results.map { results ->
                InfoCharacters(
                    name = results.name,
                    image = results.image
                )
            }
    }

}