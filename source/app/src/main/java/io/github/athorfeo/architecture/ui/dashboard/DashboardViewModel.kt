package io.github.athorfeo.architecture.ui.dashboard

import androidx.lifecycle.*
import io.github.athorfeo.architecture.repository.MovieRepository
import javax.inject.Inject

class DashboardViewModel @Inject constructor(movieRepository: MovieRepository): ViewModel() {
    private val _messages = MediatorLiveData<String>()
    val messages: LiveData<String> = _messages

    init {
        _messages.addSource(
            movieRepository
                .getMessages()
                .asLiveData()
        ){
            _messages.value = it
        }
    }
}