package com.arrkariz.capstoneprojectmade.ui.detail

import com.arrkariz.core.data.source.Resource
import com.arrkariz.core.domain.model.DetailGame
import com.arrkariz.core.domain.usecase.GameInteractor
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailGameViewModelTest {

    private lateinit var viewModel: DetailGameViewModel


    @Mock
    private lateinit var gameUseCase: GameInteractor


    @Before
    fun setUp() {
        viewModel = DetailGameViewModel(gameUseCase)

    }

    @Test
    fun getDetailGame() {
        val data = DetailGame(802, "GOW", "T", "T", "t", 5.0, true)
        val dummyGame = Resource.Success(listOf(data))
        val dummyDetailGame = flow {
            emit(dummyGame)
        }

        `when`(gameUseCase.getDetailGame(802)).thenReturn(dummyDetailGame)
        val detailGameEntity = viewModel.getDetailGame(802).value?.data
        verify(gameUseCase).getDetailGame(802)
        assertEquals(detailGameEntity?.get(0)?.name, null)
    }
}