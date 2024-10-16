package com.notsatria.muuvis.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {
    val nowPlayingMovies = movieUseCase.getNowPlayingMovies().asLiveData()

    val popularMovies = movieUseCase.getPopularMovies().asLiveData()

    val topRatedMovies = movieUseCase.getTopRatedMovies().asLiveData()

    val upcomingMovies = movieUseCase.getUpcomingMovies().asLiveData()

    fun setFavoriteMovie(movie: Movie, state: Boolean) {
        movieUseCase.setFavoriteMovie(movie, state)
    }
}