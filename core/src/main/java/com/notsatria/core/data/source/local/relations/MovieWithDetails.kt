package com.notsatria.core.data.source.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.notsatria.core.data.source.local.entity.BelongsToCollectionEntity
import com.notsatria.core.data.source.local.entity.GenreEntity
import com.notsatria.core.data.source.local.entity.MovieDetailEntity
import com.notsatria.core.data.source.local.entity.ProductionCompanyEntity
import com.notsatria.core.data.source.local.entity.ProductionCountryEntity
import com.notsatria.core.data.source.local.entity.SpokenLanguageEntity

data class MovieWithDetails(
    @Embedded val movieDetail: MovieDetailEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "movie_id"
    )
    val genres: List<GenreEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "movie_id"
    )
    val productionCompanies: List<ProductionCompanyEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "movie_id"
    )
    val productionCountries: List<ProductionCountryEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "movie_id"
    )
    val spokenLanguages: List<SpokenLanguageEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "movie_id"
    )
    val belongsToCollection: BelongsToCollectionEntity?
)
