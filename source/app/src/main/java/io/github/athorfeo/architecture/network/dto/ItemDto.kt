package io.github.athorfeo.architecture.network.dto

import com.google.gson.annotations.SerializedName
import io.github.athorfeo.architecture.model.Item

data class ItemDto(
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

fun ItemDto.toDomainModel(): Item{
    return Item(id, title, thumbnail, price, soldQuantity)
}

fun List<ItemDto>.toDomainModel(): List<Item>{
    return map { Item(it.id, it.title, it.thumbnail, it.price, it.soldQuantity) }
}