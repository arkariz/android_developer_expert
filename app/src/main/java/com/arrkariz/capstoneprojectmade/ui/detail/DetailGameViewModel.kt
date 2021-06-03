package com.arrkariz.capstoneprojectmade.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arrkariz.core.domain.model.DetailGame
import com.arrkariz.core.domain.usecase.GameUseCase

class DetailGameViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun setFavoriteGame(game: DetailGame, newStatus:Boolean) =
        gameUseCase.setFavoriteGame(game, newStatus)

    fun getDetailGame(gameId: Int) = gameUseCase.getDetailGame(gameId).asLiveData()
}