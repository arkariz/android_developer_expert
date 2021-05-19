package com.arrkariz.capstoneprojectmade.di

import com.arrkariz.capstoneprojectmade.ui.home.HomeViewModel
import com.arrkariz.core.domain.usecase.GameInteractor
import com.arrkariz.core.domain.usecase.GameUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}