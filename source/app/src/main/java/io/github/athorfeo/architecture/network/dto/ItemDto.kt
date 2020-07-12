package io.github.athorfeo.architecture.network.dto

import com.google.gson.annotations.SerializedName
import io.github.athorfeo.architecture.model.Item
import io.github.athorfeo.architecture.model.Picture

data class ItemDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("pictures")
    val pictures: List<PictureDto>,

    @SerializedName("price")
    val price: Double,

    @SerializedName("sold_quantity")
    val soldQuantity: Double,

    @SerializedName("warranty")
    val warranty: String
)

fun ItemDto.toDomainModel(): Item{
    return Item(id, title, pictures.toDomainModel(), price, soldQuantity, warranty)
}

fun List<ItemDto>.toDomainModel(): List<Item>{
    return map { Item(it.id, it.title, it.pictures.toDomainModel(), it.price, it.soldQuantity, it.warranty) }
}