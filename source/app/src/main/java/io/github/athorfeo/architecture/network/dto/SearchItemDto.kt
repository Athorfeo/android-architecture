package io.github.athorfeo.architecture.network.dto

import com.google.gson.annotations.SerializedName
import io.github.athorfeo.architecture.model.SearchItem

data class SearchItemDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("thumbnail")
    val thumbnail: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("sold_quantity")
    val soldQuantity: Double
)

fun List<SearchItemDto>.toDomainModel(): List<SearchItem>{
    return map { SearchItem(it.id, it.title, it.thumbnail, it.price, it.soldQuantity) }
}