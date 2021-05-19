package com.arrkariz.core.domain.usecase

import com.arrkariz.core.data.source.Resource
import com.arrkariz.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGames(): Flow<Resource<List<Game>>>
    fun getFavoriteGame(): Flow<List<Game>>
    fun setFavoriteTourismGame(tourism: Game, state: Boolean)
}