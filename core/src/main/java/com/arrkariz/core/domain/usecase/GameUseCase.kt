package com.arrkariz.core.domain.usecase

import com.arrkariz.core.data.source.Resource
import com.arrkariz.core.domain.model.DetailGame
import com.arrkariz.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGames(): Flow<Resource<List<Game>>>
    fun getDetailGame(gameId: Int): Flow<Resource<List<DetailGame>>>
    fun getFavoriteGame(): Flow<List<DetailGame>>
    fun setFavoriteGame(game: DetailGame, state: Boolean)
}