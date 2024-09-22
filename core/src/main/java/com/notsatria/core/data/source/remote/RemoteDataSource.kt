package com.notsatria.core.data.source.remote

import android.util.Log
import com.notsatria.core.data.source.remote.network.ApiResponse
import com.notsatria.core.data.source.remote.network.ApiService
import com.notsatria.core.data.source.remote.response.GenreResponse
import com.notsatria.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import timber.log.Timber.Forest.d
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
                    d("Now Playing", dataList.toString())
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                d("Now Playing", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieGenres(): Flow<ApiResponse<List<GenreResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieGenres()
                val dataList = response.genres
                if (dataList.isEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(dataList))
                    d("Genres", dataList.toString())
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                d("Genres", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}