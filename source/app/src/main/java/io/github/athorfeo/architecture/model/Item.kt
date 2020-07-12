package io.github.athorfeo.architecture.model

data class Item(
    val id: String,
    val title: String,
    val pictures: List<Picture>,
    val price: Double,
    val soldQuantity: Double,
    val warranty: String
)