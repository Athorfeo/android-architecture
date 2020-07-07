package io.github.athorfeo.architecture.model

data class Item(
    val id: String,
    val title: String,
    val thumbnail: String,
    val price: Double,
    val soldQuantity: Double
)