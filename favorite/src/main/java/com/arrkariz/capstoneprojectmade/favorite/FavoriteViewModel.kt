package com.arrkariz.capstoneprojectmade.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arrkariz.core.domain.usecase.GameUseCase

class FavoriteViewModel(gameUseCase: GameUseCase): ViewModel() {
    val games = gameUseCase.getFavoriteGame().asLiveData()
}