package com.notsatria.core.data.source.remote.network

import com.notsatria.core.data.source.remote.response.GenreResponseList
import com.notsatria.core.data.source.remote.response.MovieResponse
import com.notsatria.core.data.source.remote.response.MovieResponseList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): MovieResponseList

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("language") language: String = "en",
    ): GenreResponseList
}