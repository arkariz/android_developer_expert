package com.arrkariz.capstoneprojectmade.ui.detail

import androidx.lifecycle.*
import com.arrkariz.core.domain.model.DetailGame
import com.arrkariz.core.domain.model.Game
import com.arrkariz.core.domain.usecase.GameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailGameViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun setFavoriteGame(game: DetailGame, newStatus:Boolean) =
        gameUseCase.setFavoriteGame(game, newStatus)

    fun getDetailGame(gameId: Int) = gameUseCase.getDetailGame(gameId).asLiveData()
}