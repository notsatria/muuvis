package com.notsatria.core.data.source.local

import com.notsatria.core.data.source.local.entity.MovieEntity
import com.notsatria.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getMoviesByType(movieTypeId: Int): Flow<List<MovieEntity>> =
        movieDao.getMoviesByType(movieTypeId)

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    suspend fun updateFavoriteMovie(id: Int, isFavorite: Boolean) =
        movieDao.updateFavoriteMovie(id, isFavorite)

    fun searchMovies(query: String): Flow<List<MovieEntity>> = movieDao.searchMovies(query)
}