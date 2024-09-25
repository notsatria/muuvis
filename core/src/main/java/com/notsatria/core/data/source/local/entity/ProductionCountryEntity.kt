package com.notsatria.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "production_countries")
data class ProductionCountryEntity(
    @PrimaryKey(autoGenerate = true) val countryId: Int = 0,
    @ColumnInfo(name = "iso_3166_1") val iso31661: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "movie_id") val movieId: Int // Foreign key to MovieDetailEntity
)