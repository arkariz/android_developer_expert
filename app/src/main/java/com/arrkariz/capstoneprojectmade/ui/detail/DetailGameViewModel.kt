package com.arrkariz.capstoneprojectmade.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arrkariz.core.domain.model.Game
import com.arrkariz.core.domain.usecase.GameUseCase

class DetailGameViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun setFavoriteTourism(game: Game, newStatus:Boolean) =
        gameUseCase.setFavoriteGame(game, newStatus)

    suspend fun setDescGame(gameId: Int) = gameUseCase.setDescGame(gameId).asLiveData()
}