package com.notsatria.core.domain.usecase

import com.notsatria.core.domain.model.Movie
import com.notsatria.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>
}