package com.notsatria.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.notsatria.core.utils.Converters

@Entity(tableName = "movie")
data class
MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo("id")
    var id: Int,

    @ColumnInfo("overview")
    var overview: String,

    @ColumnInfo("originalLanguage")
    var originalLanguage: String,

    @ColumnInfo("originalTitle")
    var originalTitle: String,

    @ColumnInfo("video")
    var video: Boolean,

    @ColumnInfo("title")
    var title: String,

    @TypeConverters(Converters::class)
    @ColumnInfo("genreIds")
    var genreIds: List<Int>,

    @ColumnInfo("posterPath")
    var posterPath: String,

    @ColumnInfo("backdropPath")
    var backdropPath: String,

    @ColumnInfo("releaseDate")
    var releaseDate: String,

    @ColumnInfo("popularity")
    var popularity: Double,

    @ColumnInfo("voteAverage")
    var voteAverage: Double,

    @ColumnInfo("adult")
    var adult: Boolean,

    @ColumnInfo("viteCount")
    var voteCount: Int,

    @ColumnInfo("isFavorite")
    var isFavorite: Boolean = false,

    // movie type: 0 - All, 1 - Now Playing, 2 - Popular, 3 - Top Rated, 4 - Upcoming
    @ColumnInfo("movieType")
    var movieType: Int
)