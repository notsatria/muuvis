package com.notsatria.core.utils

import com.notsatria.core.data.source.local.entity.MovieEntity
import com.notsatria.core.data.source.remote.response.MovieResponse
import com.notsatria.core.domain.model.Movie

object MovieDataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>, movieType: Int): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                overview = it.overview,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                originalTitle = it.originalTitle,
                originalLanguage = it.originalLanguage,
                popularity = it.popularity,
                adult = it.adult,
                video = it.video,
                genreIds = it.genreIds,
                movieType = movieType,
                isFavorite = false,
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                originalTitle = it.originalTitle,
                originalLanguage = it.originalLanguage,
                popularity = it.popularity,
                adult = it.adult,
                video = it.video,
                genreIds = it.genreIds,
                isFavorite = it.isFavorite,
                movieType = it.movieType
            )
        }
    }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        overview = input.overview,
        title = input.title,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        originalTitle = input.originalTitle,
        originalLanguage = input.originalLanguage,
        popularity = input.popularity,
        adult = input.adult,
        video = input.video,
        genreIds = input.genreIds,
        movieType = input.movieType,
        isFavorite = input.isFavorite,
    )
}