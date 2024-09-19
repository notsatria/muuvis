package com.notsatria.muuvis.core.domain.usecase

import com.notsatria.muuvis.core.domain.model.Movie
import com.notsatria.muuvis.core.domain.repository.IMovieRepository
import com.notsatria.muuvis.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getNowPlayingMovies()
    }
}