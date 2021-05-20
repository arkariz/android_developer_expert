package com.arrkariz.core.data.source.remote

import android.util.Log
import com.arrkariz.core.data.source.remote.network.ApiResponse
import com.arrkariz.core.data.source.remote.network.ApiService
import com.arrkariz.core.data.source.remote.response.DetailGameResponse
import com.arrkariz.core.data.source.remote.response.GameData
import com.arrkariz.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllGames(): Flow<ApiResponse<GameResponse>> {
        return flow {
            try {
                val response = apiService.getGameList()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDescGame(gameId: Int): Flow<DetailGameResponse> {
        return flow {
            val response = apiService.getDetailGame(gameId)
            if (response.description.isNotEmpty()) {
                emit(response)
            }
        }.flowOn(Dispatchers.IO)
    }
}