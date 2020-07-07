package io.github.athorfeo.architecture.network

import io.github.athorfeo.architecture.model.Item
import io.github.athorfeo.architecture.network.dto.ItemDto
import io.github.athorfeo.architecture.network.response.ItemResponse
import io.github.athorfeo.architecture.network.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("sites/MCO/search")
    suspend fun search(@Query("q") query: String) : Response<SearchResponse>

    @GET("items")
    suspend fun searchById(@Query("ids") query: String) : Response<List<ItemResponse>>
}