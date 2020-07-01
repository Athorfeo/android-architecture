package io.github.athorfeo.architecture.repository

import io.github.athorfeo.architecture.database.dao.MovieDao
import io.github.athorfeo.architecture.database.entity.toDomainModel
import io.github.athorfeo.architecture.model.Movie
import io.github.athorfeo.architecture.model.Resource
import io.github.athorfeo.architecture.model.Status
import io.github.athorfeo.architecture.network.Api
import io.github.athorfeo.architecture.network.dto.MovieDto
import io.github.athorfeo.architecture.network.dto.toDatabaseModel
import io.github.athorfeo.architecture.network.dto.toDomainModel
import io.github.athorfeo.architecture.network.response.ApiEmptyResponse
import io.github.athorfeo.architecture.network.response.ApiErrorResponse
import io.github.athorfeo.architecture.network.response.ApiResponse
import io.github.athorfeo.architecture.network.response.ApiSuccessResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val api: Api
) {
    fun getFromNetwork(): Flow<Resource<List<Movie>>> {
        val flow = flow {
            val response = api.searchMovies("f33b50a94cda8f40e747fbca8ce620f8", "batman", "1")

            when(val apiResponse = ApiResponse.create(response)){
                is ApiSuccessResponse -> {
                    //emit(Resource.loading(null, "Saving data in database..."))
                    //movieDao.insertAll(*apiResponse.body.results.toDatabaseModel().toTypedArray())

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

    fun getFromDatabase() : Flow<Resource<List<Movie>>>{
        val flow = flow{
            movieDao.getMovies().collect {
                if(it.isNotEmpty()){
                    emit(Resource.success(it.toDomainModel()))
                }else{
                    getFromNetwork().collect {service ->
                        emit(service)
                    }
                }
            }
        }

        return flow
            .onStart { emit(Resource.loading(null, "Loading from database...")) }
            .catch {exception ->
                with(exception){
                    emit(Resource.error(null, 0, message))
                }
            }
            .flowOn(Dispatchers.IO)
    }
}