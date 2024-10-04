package com.notsatria.muuvis.di

import com.notsatria.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface SearchModuleDependencies {

    fun movieUseCase(): MovieUseCase
}