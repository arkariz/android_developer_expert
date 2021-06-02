package com.arrkariz.core.data.source.local

import com.arrkariz.core.data.source.local.entity.DetailGameEntity
import com.arrkariz.core.data.source.local.entity.GameEntity
import com.arrkariz.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {
    fun getAllGames(): Flow<List<GameEntity>> = gameDao.getAllGames()

    fun getDetailGame(gameId: Int): Flow<List<DetailGameEntity>> = gameDao.getDetailGame(gameId)

    fun getFavoriteGame(): Flow<List<DetailGameEntity>> = gameDao.getFavoriteGame()

    suspend fun insertGame(gameList: List<GameEntity>) = gameDao.insertGame(gameList)

    suspend fun insertDetailGame(detailGame: DetailGameEntity) = gameDao.insertDetailGame(detailGame)

    fun setFavoriteGame(game: DetailGameEntity, newState: Boolean) {
        val cleanDetail = game.desc.replace("<p>","").replace("</p>", "")
            .replace("<br />","")
        game.isFavorite = newState
        game.desc = cleanDetail
        gameDao.updateFavoriteGame(game)
    }
}