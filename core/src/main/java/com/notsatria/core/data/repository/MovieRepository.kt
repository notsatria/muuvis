package com.notsatria.core.data.repository

import android.util.Log
import com.notsatria.core.data.source.NetworkBoundResource
import com.notsatria.core.data.source.local.LocalDataSource
import com.notsatria.core.data.source.remote.RemoteDataSource
import com.notsatria.core.data.source.remote.network.ApiResponse
import com.notsatria.core.data.source.remote.response.MovieResponse
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.domain.model.MovieDetail
import com.notsatria.core.domain.repository.IMovieRepository
import com.notsatria.core.utils.MovieDataMapper
import com.notsatria.core.utils.Resource
import com.notsatria.core.utils.toDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import timber.log.Timber.Forest.d
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMovieRepository {
    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getNowPlayingMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = MovieDataMapper.mapResponsesToEntities(data, 1)
                localDataSource.insertMovies(movieList)
            }

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMoviesByType(1).map {
                    MovieDataMapper.mapEntitiesToDomain(it)
                }
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            MovieDataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = MovieDataMapper.mapDomainToEntity(movie)
        movieEntity.isFavorite = state
        runBlocking {
            withContext(Dispatchers.IO) {
                localDataSource.updateFavoriteMovie(movieEntity.id, state)
            }
        }
    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getMovieDetail(movieId).first()) {
            is ApiResponse.Success -> {
                Log.d("getMovieDetail", "${apiResponse.data}")
                emit(Resource.Success(apiResponse.data.toDomainModel()))
            }

            is ApiResponse.Empty -> {
                d("getMovieDetail", "Empty")
                emit(Resource.Error("Empty"))
            }

            is ApiResponse.Error -> {
                d("getMovieDetail", "Error: ${apiResponse.errorMessage}")
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMoviesByType(2).map {
                    MovieDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = MovieDataMapper.mapResponsesToEntities(data, 2)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMoviesByType(3).map {
                    MovieDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getTopRatedMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = MovieDataMapper.mapResponsesToEntities(data, 3)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getUpcomingMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMoviesByType(4).map {
                    MovieDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getUpcomingMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = MovieDataMapper.mapResponsesToEntities(data, 4)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun searchMovies(query: String): Flow<List<Movie>> {
        return localDataSource.searchMovies(query).map {
            MovieDataMapper.mapEntitiesToDomain(it)
        }
    }
}