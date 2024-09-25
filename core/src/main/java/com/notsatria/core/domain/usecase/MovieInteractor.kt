package com.notsatria.core.domain.usecase

import com.notsatria.core.domain.model.Movie
import com.notsatria.core.domain.model.MovieDetail
import com.notsatria.core.domain.repository.IMovieRepository
import com.notsatria.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getNowPlayingMovies()
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovies()
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        movieRepository.setFavoriteMovie(movie, state)
    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>> {
        return movieRepository.getMovieDetail(movieId)
    }
}