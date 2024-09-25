package com.notsatria.muuvis.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.notsatria.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) :
    ViewModel() {

    fun getMovieDetail(movieId: Int) = movieUseCase.getMovieDetail(movieId).asLiveData()

}