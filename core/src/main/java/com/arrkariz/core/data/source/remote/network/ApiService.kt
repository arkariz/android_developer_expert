package com.arrkariz.core.data.source.remote.network

import com.arrkariz.core.BuildConfig
import com.arrkariz.core.data.source.remote.response.DetailGameResponse
import com.arrkariz.core.data.source.remote.response.GameData
import com.arrkariz.core.data.source.remote.response.GameResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object{
        const val token = BuildConfig.RAWG_TOKEN
    }
    @GET("games?key=$token")
    suspend fun getGameList(): GameResponse

    @GET("games/{id}?key=$token")
    fun getDetailGame(@Path("id") id: Int): DetailGameResponse
}