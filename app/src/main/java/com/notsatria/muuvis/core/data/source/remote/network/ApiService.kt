package com.notsatria.muuvis.core.data.source.remote.network

import com.notsatria.muuvis.core.data.source.remote.response.MovieResponse
import com.notsatria.muuvis.core.data.source.remote.response.MovieResponseList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): MovieResponseList
}