package com.notsatria.core.data.repository

import com.notsatria.core.data.source.NetworkBoundResource
import com.notsatria.core.data.source.local.LocalDataSource
import com.notsatria.core.data.source.remote.RemoteDataSource
import com.notsatria.core.data.source.remote.network.ApiResponse
import com.notsatria.core.data.source.remote.response.GenreResponse
import com.notsatria.core.data.source.remote.response.MovieResponse
import com.notsatria.core.domain.model.Genre
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.domain.repository.IMovieRepository
import com.notsatria.core.utils.DataMapper
import com.notsatria.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
                val movieList = DataMapper.mapResponsesToEntities(data, 1)
                localDataSource.insertMovies(movieList)
            }

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMoviesByType(1).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }
        }.asFlow()

    override fun getMovieGenres(): Flow<Resource<List<Genre>>> =
        object : NetworkBoundResource<List<Genre>, List<GenreResponse>>() {
            override fun shouldFetch(data: List<Genre>?): Boolean {
                return true
            }

            override fun loadFromDB(): Flow<List<Genre>> {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GenreResponse>>> {
                return remoteDataSource.getMovieGenres()
            }

            override suspend fun saveCallResult(data: List<GenreResponse>) {
                // TODO
            }
        }.asFlow()
}