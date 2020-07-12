package io.github.athorfeo.architecture.network.dto

import com.google.gson.annotations.SerializedName
import io.github.athorfeo.architecture.model.Picture

data class PictureDto (
    @SerializedName("id")
    val id: String,

    @SerializedName("secure_url")
    val secureUrl: String
)

fun List<PictureDto>.toDomainModel(): List<Picture>{
    return map { Picture(it.id, it.secureUrl) }
}