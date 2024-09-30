package com.notsatria.core.domain.repository

import com.notsatria.core.domain.model.Movie
import com.notsatria.core.domain.model.MovieDetail
import com.notsatria.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>>

    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>

    fun getUpcomingMovies(): Flow<Resource<List<Movie>>>

    fun searchMovies(query: String): Flow<List<Movie>>
}