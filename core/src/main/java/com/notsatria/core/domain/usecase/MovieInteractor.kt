package com.notsatria.core.domain.usecase

import com.notsatria.core.domain.model.Movie
import com.notsatria.core.domain.repository.IMovieRepository
import com.notsatria.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getNowPlayingMovies()
    }
}