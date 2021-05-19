package com.arrkariz.core.data.source

import com.arrkariz.core.data.source.local.LocalDataSource
import com.arrkariz.core.data.source.remote.RemoteDataSource
import com.arrkariz.core.data.source.remote.network.ApiResponse
import com.arrkariz.core.data.source.remote.response.GameData
import com.arrkariz.core.data.source.remote.response.GameResponse
import com.arrkariz.core.domain.model.Game
import com.arrkariz.core.domain.repository.IGameRepository
import com.arrkariz.core.utils.AppExecutors
import com.arrkariz.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {

    override fun getAllGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, GameResponse>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGames().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> =
                remoteDataSource.getAllGames()

            override suspend fun saveCallResult(data: GameResponse) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(tourismList)
            }
        }.asFlow()

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(tourism: Game, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(tourismEntity, state) }
    }
}

