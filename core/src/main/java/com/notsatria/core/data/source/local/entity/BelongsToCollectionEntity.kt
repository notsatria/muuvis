package com.notsatria.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "belongs_to_collection")
data class BelongsToCollectionEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "poster_path") val posterPath: String?,
    @ColumnInfo(name = "movie_id") val movieId: Int // Foreign key to MovieDetailEntity
)