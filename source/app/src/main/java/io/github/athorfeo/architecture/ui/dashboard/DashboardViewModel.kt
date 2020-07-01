package io.github.athorfeo.architecture.ui.dashboard

import androidx.lifecycle.*
import io.github.athorfeo.architecture.repository.MessagesRepository
import io.github.athorfeo.architecture.repository.MovieRepository
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    messagesRepository: MessagesRepository,
    movieRepository: MovieRepository
): ViewModel() {
    //1
    private val _messages = MediatorLiveData<String>()
    val messages: LiveData<String> = _messages

    init {
        _messages.addSource(
            messagesRepository
                .getMessages()
                .asLiveData()
        ){
            _messages.value = it
        }
    }

    //2
    val movies = movieRepository.getFromDatabase().asLiveData()
}