package com.arrkariz.capstoneprojectmade.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arrkariz.core.domain.usecase.GameUseCase

class HomeViewModel(gameUseCase: GameUseCase) : ViewModel(){

    val game = gameUseCase.getAllGames().asLiveData()
}