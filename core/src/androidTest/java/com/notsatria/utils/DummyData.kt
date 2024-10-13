package com.notsatria.utils

import com.notsatria.core.data.source.local.entity.MovieEntity

object DummyData {
    fun generateMovieEntities(): List<MovieEntity> {
        val movieEntities = mutableListOf<MovieEntity>()
        repeat(10) {
            val movieEntity = MovieEntity(
                id = it,
                title = "Movie $it",
                overview = "Overview $it",
                posterPath = "poster$it.jpg",
                backdropPath = "backdrop$it.jpg",
                releaseDate = "2023-01-01",
                voteAverage = 7.5,
                isFavorite = it % 2 == 0,
                movieType = 0,
                genreIds = listOf(1, 2, 3),
                originalTitle = "Original Title $it",
                originalLanguage = "Original Language $it",
                popularity = 10.0,
                video = false,
                adult = false,
                voteCount = 100,
            )
            movieEntities.add(movieEntity)
        }
        return movieEntities
    }
}