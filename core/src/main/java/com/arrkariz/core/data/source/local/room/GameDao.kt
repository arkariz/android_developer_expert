package com.arrkariz.core.data.source.local.room

import androidx.room.*
import com.arrkariz.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games where isFavorite = 1")
    fun getFavoriteGame(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games where gameId = :gameId")
    fun getDescGame(gameId: Int): Flow<GameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: List<GameEntity>)

    @Update
    fun updateFavoriteGame(game: GameEntity)
}