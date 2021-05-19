package com.arrkariz.core.domain.usecase

import com.arrkariz.core.data.source.Resource
import com.arrkariz.core.domain.model.Game
import com.arrkariz.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getAllGames(): Flow<Resource<List<Game>>> = gameRepository.getAllGames()

    override fun getFavoriteGame(): Flow<List<Game>> = gameRepository.getFavoriteGame()

    override fun setFavoriteTourismGame(game: Game, state: Boolean) = gameRepository.setFavoriteGame(game, state)
}