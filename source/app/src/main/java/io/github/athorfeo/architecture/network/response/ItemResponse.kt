package io.github.athorfeo.architecture.network.response

import com.google.gson.annotations.SerializedName
import io.github.athorfeo.architecture.network.dto.ItemDto
import io.github.athorfeo.architecture.network.dto.SearchItemDto

data class ItemResponse(
    @SerializedName("code")
    val code: String,

    @SerializedName("body")
    val body: ItemDto
)