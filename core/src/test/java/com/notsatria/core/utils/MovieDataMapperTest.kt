package com.notsatria.core.utils

import com.notsatria.core.data.source.local.entity.MovieEntity
import com.notsatria.core.data.source.remote.response.MovieResponse
import com.notsatria.core.domain.model.Movie
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieDataMapperTest {

    @Test
    fun `mapResponsesToEntities correctly maps list of MovieResponse to list of MovieEntity`() {
        val movieResponses = listOf(
            MovieResponse(
                id = 1,
                overview = "Test overview",
                title = "Test title",
                posterPath = "/poster.jpg",
                backdropPath = "/backdrop.jpg",
                releaseDate = "2023-01-01",
                voteAverage = 7.5,
                voteCount = 100,
                originalTitle = "Original Test Title",
                originalLanguage = "en",
                popularity = 50.0,
                adult = false,
                video = false,
                genreIds = listOf(1, 2, 3)
            )
        )

        val movieType = 1
        val result = MovieDataMapper.mapResponsesToEntities(movieResponses, movieType)

        assertEquals(1, result.size)
        with(result[0]) {
            assertEquals(1, id)
            assertEquals("Test overview", overview)
            assertEquals("Test title", title)
            assertEquals("/poster.jpg", posterPath)
            assertEquals("/backdrop.jpg", backdropPath)
            assertEquals("2023-01-01", releaseDate)
            assertEquals(7.5, voteAverage, 0.01)
            assertEquals(100, voteCount)
            assertEquals("Original Test Title", originalTitle)
            assertEquals("en", originalLanguage)
            assertEquals(50.0, popularity, 0.01)
            assertFalse(adult)
            assertFalse(video)
            assertEquals(listOf(1, 2, 3), genreIds)
            assertEquals(1, movieType)
            assertFalse(isFavorite)
        }
    }

    @Test
    fun `mapEntitiesToDomain correctly maps list of MovieEntity to list of Movie`() {
        val movieEntities = listOf(
            MovieEntity(
                id = 1,
                overview = "Test overview",
                title = "Test title",
                posterPath = "/poster.jpg",
                backdropPath = "/backdrop.jpg",
                releaseDate = "2023-01-01",
                voteAverage = 7.5,
                voteCount = 100,
                originalTitle = "Original Test Title",
                originalLanguage = "en",
                popularity = 50.0,
                adult = false,
                video = false,
                genreIds = listOf(1, 2, 3),
                movieType = 1,
                isFavorite = true
            )
        )

        val result = MovieDataMapper.mapEntitiesToDomain(movieEntities)

        assertEquals(1, result.size)
        with(result[0]) {
            assertEquals(1, id)
            assertEquals("Test overview", overview)
            assertEquals("Test title", title)
            assertEquals("/poster.jpg", posterPath)
            assertEquals("/backdrop.jpg", backdropPath)
            assertEquals("2023-01-01", releaseDate)
            assertEquals(7.5, voteAverage, 0.01)
            assertEquals(100, voteCount)
            assertEquals("Original Test Title", originalTitle)
            assertEquals("en", originalLanguage)
            assertEquals(50.0, popularity, 0.01)
            assertFalse(adult)
            assertFalse(video)
            assertEquals(listOf(1, 2, 3), genreIds)
            assertEquals(1, movieType)
            assertTrue(isFavorite)
        }
    }

    @Test
    fun `mapDomainToEntity correctly maps Movie to MovieEntity`() {
        val movie = Movie(
            id = 1,
            overview = "Test overview",
            title = "Test title",
            posterPath = "/poster.jpg",
            backdropPath = "/backdrop.jpg",
            releaseDate = "2023-01-01",
            voteAverage = 7.5,
            voteCount = 100,
            originalTitle = "Original Test Title",
            originalLanguage = "en",
            popularity = 50.0,
            adult = false,
            video = false,
            genreIds = listOf(1, 2, 3),
            movieType = 1,
            isFavorite = true
        )

        val result = MovieDataMapper.mapDomainToEntity(movie)

        with(result) {
            assertEquals(1, id)
            assertEquals("Test overview", overview)
            assertEquals("Test title", title)
            assertEquals("/poster.jpg", posterPath)
            assertEquals("/backdrop.jpg", backdropPath)
            assertEquals("2023-01-01", releaseDate)
            assertEquals(7.5, voteAverage, 0.01)
            assertEquals(100, voteCount)
            assertEquals("Original Test Title", originalTitle)
            assertEquals("en", originalLanguage)
            assertEquals(50.0, popularity, 0.01)
            assertFalse(adult)
            assertFalse(video)
            assertEquals(listOf(1, 2, 3), genreIds)
            assertEquals(1, movieType)
            assertTrue(isFavorite)
        }
    }
}