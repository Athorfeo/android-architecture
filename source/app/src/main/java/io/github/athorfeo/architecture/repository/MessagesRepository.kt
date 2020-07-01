package io.github.athorfeo.architecture.repository

import io.github.athorfeo.architecture.database.dao.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MessagesRepository @Inject constructor(movieDao: MovieDao) {
    fun getMessages(): Flow<String> {
        val flow = flow {
            for(i in 1..10){
                delay(100)
                emit("Message $i")
            }
        }

        return flow
            .onStart { emit("Messages are fetching..") }
            .catch { emit("Error fetching messagges...") }
            .flowOn(Dispatchers.IO)
    }
}