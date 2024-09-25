package com.notsatria.core.domain.model

// Domain Model for Movie Detail
data class MovieDetail(
    val id: Int,
    val title: String,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val releaseDate: String,
    val runtime: Int,
    val budget: Int,
    val revenue: Int,
    val imdbId: String?,
    val homepage: String?,
    val genres: List<Genre>,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val spokenLanguages: List<SpokenLanguage>,
    val belongsToCollection: BelongsToCollection?,
    val popularity: Double,
    val voteCount: Int,
    val tagline: String?,
    val status: String,
    val video: Boolean,
    val adult: Boolean
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val name: String,
    val logoPath: String?,
    val originCountry: String
)

data class ProductionCountry(
    val iso31661: String,
    val name: String
)

data class SpokenLanguage(
    val iso6391: String,
    val name: String,
    val englishName: String
)

data class BelongsToCollection(
    val id: Int,
    val name: String,
    val backdropPath: String?,
    val posterPath: String?
)
