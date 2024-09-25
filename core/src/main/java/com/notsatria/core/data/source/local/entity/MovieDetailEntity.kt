package com.notsatria.core.data.source.local.entity

import androidx.room.*

@Entity(tableName = "movie_details")
data class MovieDetailEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "imdb_id") val imdbId: String?,
    @ColumnInfo(name = "video") val video: Boolean,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String?,
    @ColumnInfo(name = "revenue") val revenue: Int,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "vote_count") val voteCount: Int,
    @ColumnInfo(name = "budget") val budget: Int,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "runtime") val runtime: Int,
    @ColumnInfo(name = "poster_path") val posterPath: String?,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    @ColumnInfo(name = "tagline") val tagline: String?,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "homepage") val homepage: String?,
    @ColumnInfo(name = "status") val status: String
)