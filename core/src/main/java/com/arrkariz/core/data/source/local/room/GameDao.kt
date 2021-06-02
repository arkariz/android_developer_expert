package com.arrkariz.core.data.source.local.room

import androidx.room.*
import com.arrkariz.core.data.source.local.entity.DetailGameEntity
import com.arrkariz.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM detailgame where isFavorite = 1")
    fun getFavoriteGame(): Flow<List<DetailGameEntity>>

    @Query("SELECT * FROM detailgame where gameId = :gameId")
    fun getDetailGame(gameId: Int): Flow<List<DetailGameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailGame(detailGame: DetailGameEntity)

    @Update
    fun updateFavoriteGame(detailGame: DetailGameEntity)
}