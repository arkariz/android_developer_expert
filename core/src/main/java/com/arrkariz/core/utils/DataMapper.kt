package com.arrkariz.core.utils

import com.arrkariz.core.data.source.local.entity.DetailGameEntity
import com.arrkariz.core.data.source.local.entity.GameEntity
import com.arrkariz.core.data.source.remote.response.DetailGameResponse
import com.arrkariz.core.data.source.remote.response.GameResponse
import com.arrkariz.core.domain.model.DetailGame
import com.arrkariz.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: GameResponse): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()

        input.results.map { it ->
            val game = GameEntity(
                gameId = it.id,
                name = it.name,
                relesead = it.released,
                image = it.background_image,
                rating = it.rating,
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.gameId,
                name = it.name,
                released = it.relesead,
                background_image = it.image,
                rating = it.rating,
            )
        }

    fun mapDetailGameDomainToEntity(input: DetailGame) = DetailGameEntity(
        gameId = input.id,
        name = input.name,
        desc = input.desc,
        relesead = input.released,
        image = input.background_image,
        rating = input.rating,
        isFavorite = input.isFavorite
    )

    fun mapDetailGameEntityToDomain(input: List<DetailGameEntity>): List<DetailGame> =
        input.map {
            DetailGame(
                id = it.gameId,
                name = it.name,
                desc = it.desc,
                released = it.relesead,
                background_image = it.image,
                rating = it.rating,
                isFavorite = it.isFavorite
            )
        }

    fun mapDetailGameResponseToEntity(input: DetailGameResponse): DetailGameEntity {
        val cleanDetail = input.description.replace("<p>", "").replace("</p>", "")
            .replace("<br />", "")

        return DetailGameEntity(
            gameId = input.id,
            name = input.name,
            desc = cleanDetail,
            relesead = input.released,
            image = input.background_image,
            rating = input.rating,
            isFavorite = false
        )
    }

    fun mapDetailGameEntitiesToDomain(input: List<DetailGameEntity>): List<DetailGame> =
        input.map {
            DetailGame(
                id = it.gameId,
                name = it.name,
                desc = it.desc,
                released = it.relesead,
                background_image = it.image,
                rating = it.rating,
                isFavorite = it.isFavorite
            )
        }
}