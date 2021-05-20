package com.arrkariz.core.utils

import com.arrkariz.core.data.source.local.entity.GameEntity
import com.arrkariz.core.data.source.remote.response.GameData
import com.arrkariz.core.data.source.remote.response.GameResponse
import com.arrkariz.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: GameResponse): List<GameEntity> {
        val tourismList = ArrayList<GameEntity>()

        input.results.map { it ->
            val tourism = GameEntity(
                gameId = it.id,
                name = it.name,
                desc = " ",
                relesead = it.released,
                image = it.background_image,
                rating = it.rating,
                isFavorite = false
            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.gameId,
                name = it.name,
                desc = it.desc,
                released = it.relesead,
                background_image = it.image,
                rating = it.rating,
                isFavorite = it.isFavorite
            )
        }

    fun mapEntityToDomain(input: GameEntity): Game =
        Game(
            id = input.gameId,
            name = input.name,
            desc = input.desc,
            released = input.relesead,
            background_image = input.image,
            rating = input.rating,
            isFavorite = input.isFavorite
        )

    fun mapDomainToEntity(input: Game) = GameEntity(
        gameId = input.id,
        name = input.name,
        desc = input.desc,
        relesead = input.released,
        image = input.background_image,
        rating = input.rating,
        isFavorite = input.isFavorite
    )
}