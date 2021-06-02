package com.arrkariz.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arrkariz.core.data.source.local.entity.DetailGameEntity
import com.arrkariz.core.data.source.local.entity.GameEntity

@Database(entities = [GameEntity::class, DetailGameEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}