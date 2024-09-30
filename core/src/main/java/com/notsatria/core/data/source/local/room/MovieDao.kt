package com.notsatria.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.notsatria.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE movieType = 0")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE movieType = :movieTypeId")
    fun getMoviesByType(movieTypeId: Int): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("UPDATE movie SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteMovie(id: Int, isFavorite: Boolean)

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :query || '%' " +
            "OR overview LIKE '%' || :query || '%' " +
            "OR releaseDate LIKE '%' || :query || '%' " +
            " ORDER BY releaseDate DESC")
    fun searchMovies(query: String): Flow<List<MovieEntity>>
}