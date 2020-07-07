package io.github.athorfeo.architecture.model

data class SearchItem(
    val id: String,
    val title: String,
    val thumbnail: String,
    val price: Double,
    val soldQuantity: Double
)