package io.github.athorfeo.architecture.repository

import io.github.athorfeo.architecture.model.Item
import io.github.athorfeo.architecture.model.Resource
import io.github.athorfeo.architecture.model.SearchItem
import io.github.athorfeo.architecture.network.Api
import io.github.athorfeo.architecture.network.dto.toDomainModel
import io.github.athorfeo.architecture.network.response.ApiEmptyResponse
import io.github.athorfeo.architecture.network.response.ApiErrorResponse
import io.github.athorfeo.architecture.network.response.ApiResponse
import io.github.athorfeo.architecture.network.response.ApiSuccessResponse
import io.github.athorfeo.architecture.testing.OpenForTesting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OpenForTesting
class ItemsRepository @Inject constructor(private val api: Api) {

    fun search(query: String): Flow<Resource<List<SearchItem>>> {
        val flow = flow {
            val response = api.search(query)

            when(val apiResponse = ApiResponse.create(response)){
                is ApiSuccessResponse -> {
                    emit(Resource.success(apiResponse.body.results.toDomainModel()))
                }
                is ApiEmptyResponse -> {
                    emit(Resource.error(null, 0, "Empty response"))
                }
                is ApiErrorResponse -> {
                    emit(Resource.error(null, apiResponse.code, apiResponse.message))
                }
            }
        }

        return flow
            .onStart { emit(Resource.loading(null, "Service fetching...")) }
            .catch {exception ->
                with(exception){
                    emit(Resource.error(null, 0, message))
                }
            }
            .flowOn(Dispatchers.IO)
    }

    fun searchById(id: String): Flow<Resource<Item>> {
        val flow = flow {
            val response = api.searchById(id)

            when(val apiResponse = ApiResponse.create(response)){
                is ApiSuccessResponse -> {
                    emit(Resource.success(apiResponse.body[0].body.toDomainModel()))
                }
                is ApiEmptyResponse -> {
                    emit(Resource.error(null, 0, "Empty response"))
                }
                is ApiErrorResponse -> {
                    emit(Resource.error(null, apiResponse.code, apiResponse.message))
                }
            }
        }

        return flow
            .onStart { emit(Resource.loading(null, "Service fetching...")) }
            .catch {exception ->
                with(exception){
                    emit(Resource.error(null, 0, message))
                }
            }
            .flowOn(Dispatchers.IO)
    }
}