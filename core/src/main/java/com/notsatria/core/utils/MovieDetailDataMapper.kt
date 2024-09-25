package com.notsatria.core.utils

import com.notsatria.core.data.source.remote.response.BelongsToCollectionResponse
import com.notsatria.core.data.source.remote.response.GenresItem
import com.notsatria.core.data.source.remote.response.MovieDetailResponse
import com.notsatria.core.data.source.remote.response.ProductionCompaniesItem
import com.notsatria.core.data.source.remote.response.ProductionCountriesItem
import com.notsatria.core.data.source.remote.response.SpokenLanguagesItem
import com.notsatria.core.domain.model.BelongsToCollection
import com.notsatria.core.domain.model.Genre
import com.notsatria.core.domain.model.MovieDetail
import com.notsatria.core.domain.model.ProductionCompany
import com.notsatria.core.domain.model.ProductionCountry
import com.notsatria.core.domain.model.SpokenLanguage

// Mapper function to convert MovieDetailResponse to MovieDetail domain model
fun MovieDetailResponse.toDomainModel(): MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        runtime = runtime,
        budget = budget,
        revenue = revenue,
        imdbId = imdbId,
        homepage = homepage,
        genres = genres.map { it.toDomainModel() },
        productionCompanies = productionCompanies.map { it.toDomainModel() },
        productionCountries = productionCountries.map { it.toDomainModel() },
        spokenLanguages = spokenLanguages.map { it.toDomainModel() },
        belongsToCollection = belongsToCollection?.toDomainModel(),
        popularity = popularity,
        voteCount = voteCount,
        tagline = tagline,
        status = status,
        video = video,
        adult = adult
    )
}

// Mapper function to convert GenresItem to Genre domain model
fun GenresItem.toDomainModel(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

// Mapper function to convert ProductionCompaniesItem to ProductionCompany domain model
fun ProductionCompaniesItem.toDomainModel(): ProductionCompany {
    return ProductionCompany(
        id = id,
        name = name,
        logoPath = logoPath,
        originCountry = originCountry
    )
}

// Mapper function to convert ProductionCountriesItem to ProductionCountry domain model
fun ProductionCountriesItem.toDomainModel(): ProductionCountry {
    return ProductionCountry(
        iso31661 = iso31661,
        name = name
    )
}

// Mapper function to convert SpokenLanguagesItem to SpokenLanguage domain model
fun SpokenLanguagesItem.toDomainModel(): SpokenLanguage {
    return SpokenLanguage(
        iso6391 = iso6391,
        name = name,
        englishName = englishName
    )
}

// Mapper function to convert BelongsToCollection to BelongsToCollection domain model
fun BelongsToCollectionResponse.toDomainModel(): BelongsToCollection {
    return BelongsToCollection(
        id = id,
        name = name,
        backdropPath = backdropPath,
        posterPath = posterPath
    )
}
