package com.example.testuigames.data

import com.example.testuigames.model.InfoCharacters

interface CharactersRepository {
    suspend fun getCharacters(): List<InfoCharacters>
    fun getCharacterById(id: Int): InfoCharacters?
}

class NetworkCharactersRepository(
    private val characterRemoteDataSource: CharacterRemoteDataSource
): CharactersRepository{
    private val charactersCache = HashMap<Int, InfoCharacters>()
    override suspend fun getCharacters(): List<InfoCharacters> {
        val characters = characterRemoteDataSource.charactersService
            .getCharactersList().results

        characters.forEach {character ->
            character.id?.let {
                charactersCache[it] = InfoCharacters(
                    id= character.id,
                    name = character.name,
                    image = character.image,
                    species = character.species,
                    episode = character.episode
                )
            }
        }
        return characters.map {results ->
            InfoCharacters(
                id = results.id,
                name = results.name,
                image = results.image,
                species = results.species,
                episode = results.episode
            )
        }
    }
    override fun getCharacterById(id: Int): InfoCharacters?{
        return charactersCache[id]
    }
}