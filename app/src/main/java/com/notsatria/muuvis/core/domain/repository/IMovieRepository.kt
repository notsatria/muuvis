package com.notsatria.muuvis.core.domain.repository

import com.notsatria.muuvis.core.domain.model.Movie
import com.notsatria.muuvis.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>
}