package com.notsatria.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun searchMovies(query: String) = movieUseCase.searchMovies(query).asLiveData()

    fun setFavoriteMovie(movie: Movie, state: Boolean) = movieUseCase.setFavoriteMovie(movie, state)
}