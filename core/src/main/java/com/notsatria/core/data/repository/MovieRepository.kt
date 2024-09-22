package com.notsatria.core.data.repository

import com.notsatria.core.data.source.NetworkBoundResource
import com.notsatria.core.data.source.remote.RemoteDataSource
import com.notsatria.core.data.source.remote.network.ApiResponse
import com.notsatria.core.data.source.remote.response.MovieResponse
import com.notsatria.core.domain.model.Movie
import com.notsatria.core.domain.repository.IMovieRepository
import com.notsatria.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IMovieRepository {
    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun shouldFetch(data: List<Movie>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getNowPlayingMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = data
            }

            override fun mapCallResult(data: List<MovieResponse>): List<Movie> {
                return data.map { response ->
                    Movie(
                        id = response.id,
                        title = response.title,
                        overview = response.overview,
                        posterPath = response.posterPath,
                        backdropPath = response.backdropPath,
                        releaseDate = response.releaseDate,
                        voteAverage = response.voteAverage,
                        voteCount = response.voteCount,
                        popularity = response.popularity,
                        adult = response.adult,
                        video = response.video,
                        originalLanguage = response.originalLanguage,
                        originalTitle = response.originalTitle,
                        genreIds = response.genreIds,
                    )
                }
            }
        }.asFlow()
}