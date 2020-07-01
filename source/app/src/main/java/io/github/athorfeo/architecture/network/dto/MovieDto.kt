package io.github.athorfeo.architecture.network.dto

import com.google.gson.annotations.SerializedName
import io.github.athorfeo.architecture.database.entity.MovieEntity
import io.github.athorfeo.architecture.model.Movie

data class MovieDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("overview")
    val overview: String
)

fun List<MovieDto>.toDomainModel(): List<Movie>{
    return map { Movie(it.id, it.title, it.originalTitle, it.overview) }
}

fun List<MovieDto>.toDatabaseModel(): List<MovieEntity>{
    return map { MovieEntity(it.id, it.title, it.originalTitle, it.overview) }
}