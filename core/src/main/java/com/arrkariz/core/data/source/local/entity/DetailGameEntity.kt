package com.arrkariz.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detailgame")
data class DetailGameEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "gameId")
    var gameId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "desc")
    var desc: String,

    @ColumnInfo(name = "released")
    var relesead: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
