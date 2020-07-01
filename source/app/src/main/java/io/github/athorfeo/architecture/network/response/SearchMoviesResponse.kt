package io.github.athorfeo.architecture.network.response

import com.google.gson.annotations.SerializedName
import io.github.athorfeo.architecture.network.dto.MovieDto

data class SearchMoviesResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieDto>,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("total_pages")
    val totalPages: Int
)