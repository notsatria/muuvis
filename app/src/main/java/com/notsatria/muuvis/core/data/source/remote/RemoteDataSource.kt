package com.notsatria.muuvis.core.data.source.remote

import android.util.Log
import com.notsatria.muuvis.core.data.source.remote.network.ApiResponse
import com.notsatria.muuvis.core.data.source.remote.network.ApiService
import com.notsatria.muuvis.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getNowPlayingMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getNowPlayingMovies()
                val dataList = response.results
                if (dataList.isEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(dataList))
                    Log.d("RemoteDataSource", dataList.toString())
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}