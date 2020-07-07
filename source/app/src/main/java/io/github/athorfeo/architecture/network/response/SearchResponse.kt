package io.github.athorfeo.architecture.network.response

import com.google.gson.annotations.SerializedName
import io.github.athorfeo.architecture.network.dto.SearchItemDto

data class SearchResponse(
    @SerializedName("site_id")
    val siteId: String,

    @SerializedName("results")
    val results: List<SearchItemDto>
)