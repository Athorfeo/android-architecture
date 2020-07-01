package io.github.athorfeo.architecture.network

import io.github.athorfeo.architecture.network.response.SearchMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("3/search/movie")
    suspend fun searchMovies(@Query("api_key") apiKey: String,
                             @Query("query") query: String,
                             @Query("page") page: String) : Response<SearchMoviesResponse>
}