package com.arrkariz.core.data.source.local

import com.arrkariz.core.data.source.local.entity.GameEntity
import com.arrkariz.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {
    fun getAllGames(): Flow<List<GameEntity>> = gameDao.getAllGames()

    fun getDescGame(gameId: Int): Flow<GameEntity> = gameDao.getDescGame(gameId)

    fun getFavoriteGame(): Flow<List<GameEntity>> = gameDao.getFavoriteGame()

    suspend fun insertGame(gameList: List<GameEntity>) = gameDao.insertGame(gameList)

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }

    fun setDescGame(game: GameEntity, desc: String) {
        val cleanDesc = desc.replace("<p>","").replace("</p>", "")
            .replace("<br />","")
        game.desc = cleanDesc
        gameDao.updateFavoriteGame(game)
    }
}