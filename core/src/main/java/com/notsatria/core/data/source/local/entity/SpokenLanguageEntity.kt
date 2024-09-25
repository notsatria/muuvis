package com.notsatria.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spoken_languages")
data class SpokenLanguageEntity(
    @PrimaryKey(autoGenerate = true) val languageId: Int = 0,
    @ColumnInfo(name = "iso_639_1") val iso6391: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "english_name") val englishName: String,
    @ColumnInfo(name = "movie_id") val movieId: Int // Foreign key to MovieDetailEntity
)