package com.arrkariz.core.data.source


import com.arrkariz.core.data.source.local.LocalDataSource
import com.arrkariz.core.data.source.remote.RemoteDataSource
import com.arrkariz.core.data.source.remote.network.ApiResponse
import com.arrkariz.core.data.source.remote.response.DetailGameResponse
import com.arrkariz.core.data.source.remote.response.GameResponse
import com.arrkariz.core.domain.model.DetailGame
import com.arrkariz.core.domain.model.Game
import com.arrkariz.core.domain.repository.IGameRepository
import com.arrkariz.core.utils.AppExecutors
import com.arrkariz.core.utils.DataMapper
import kotlinx.coroutines.flow.*

class GameRepository(
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
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(gameList)
            }
        }.asFlow()

    override fun getDetailGame(gameId: Int): Flow<Resource<List<DetailGame>>> =
        object : NetworkBoundResource<List<DetailGame>, DetailGameResponse>() {
            override fun loadFromDB(): Flow<List<DetailGame>> {
                return localDataSource.getDetailGame(gameId).map {
                    DataMapper.mapDetailGameEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<DetailGame>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<DetailGameResponse>> =
                remoteDataSource.getDescGame(gameId)

            override suspend fun saveCallResult(data: DetailGameResponse) {
                val detailGame = DataMapper.mapDetailGameResponseToEntity(data)
                localDataSource.insertDetailGame(detailGame)
            }
        }.asFlow()

    override fun getFavoriteGame(): Flow<List<DetailGame>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapDetailGameEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: DetailGame, state: Boolean) {
        val gameEntity = DataMapper.mapDetailGameDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
    }
}

