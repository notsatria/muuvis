package com.notsatria.core.data.source.local.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.notsatria.utils.DummyData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: MovieDatabase
    private lateinit var dao: MovieDao
    private val movieSample = DummyData.generateMovieEntities()[0]

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.movieDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndGetAllMoviesTest() = runTest {
        val movieList = DummyData.generateMovieEntities()
        dao.insertMovies(movieList)

        val movies = dao.getAllMovies().first()
        assert(movies.contains(movieSample))
    }

    @Test
    fun getMoviesByTypeTest() = runTest {
        val movieList = DummyData.generateMovieEntities()
        dao.insertMovies(movieList)

        val moviesResult = dao.getMoviesByType(0).first()
        assert(moviesResult.contains(movieSample))
    }

    @Test
    fun getFavoriteMoviesTest() = runTest {
        val movieList = DummyData.generateMovieEntities()
        dao.insertMovies(movieList)

        val moviesResult = dao.getFavoriteMovies().first()
        assert(moviesResult.contains(movieSample))
    }

    @Test
    fun setFavoriteMovieTest() = runTest {
        val movieList = DummyData.generateMovieEntities()
        dao.insertMovies(movieList)
        dao.updateFavoriteMovie(movieSample.id, true)

        val moviesResult = dao.getFavoriteMovies().first()
        assert(moviesResult.contains(movieSample))
    }

    @Test
    fun searchMoviesTest() = runTest {
        val movieList = DummyData.generateMovieEntities()
        dao.insertMovies(movieList)
        val moviesResult = dao.searchMovies("Movie 0").first()
        assert(moviesResult.contains(movieSample))

        val moviesResult2 = dao.searchMovies("overview 0").first()
        assert(moviesResult2.contains(movieSample))

        val moviesResult3 = dao.searchMovies("2023").first()
        assert(moviesResult3.contains(movieSample))
    }
}