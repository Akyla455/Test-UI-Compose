package com.example.testuigames.data

import com.example.testuigames.model.InfoCharacters

interface CharactersRepository {
    suspend fun getCharacters(): List<InfoCharacters>
}

class NetworkCharactersRepository(
    private val characterRemoteDataSource: CharacterRemoteDataSource
): CharactersRepository{
    override suspend fun getCharacters(): List<InfoCharacters> {
        return characterRemoteDataSource.charactersService
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