package com.notsatria.muuvis.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.notsatria.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieUseCase: MovieUseCase
) : ViewModel() {
    val nowPlayingMovies = movieUseCase.getNowPlayingMovies().asLiveData()
}