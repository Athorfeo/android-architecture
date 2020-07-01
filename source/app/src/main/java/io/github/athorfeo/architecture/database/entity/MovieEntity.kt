package io.github.athorfeo.architecture.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.athorfeo.architecture.model.Movie

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "original_title")
    val originalTitle: String,

    @ColumnInfo(name = "overview")
    val overview: String
)

fun List<MovieEntity>.toDomainModel(): List<Movie>{
    return map { Movie(it.id, it.title, it.originalTitle, it.overview) }
}