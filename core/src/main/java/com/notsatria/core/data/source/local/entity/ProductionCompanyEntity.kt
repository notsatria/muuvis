package com.notsatria.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "production_companies")
data class ProductionCompanyEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "logo_path") val logoPath: String?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "origin_country") val originCountry: String,
    @ColumnInfo(name = "movie_id") val movieId: Int // Foreign key to MovieDetailEntity
)