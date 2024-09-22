package com.notsatria.core.domain.repository

import com.notsatria.core.domain.model.Genre
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>

    fun getMovieGenres(): Flow<Resource<List<Genre>>>
}