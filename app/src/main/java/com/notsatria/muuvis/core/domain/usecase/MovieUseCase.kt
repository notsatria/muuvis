package com.notsatria.muuvis.core.domain.usecase

import com.notsatria.muuvis.core.domain.model.Movie
import com.notsatria.muuvis.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>
}