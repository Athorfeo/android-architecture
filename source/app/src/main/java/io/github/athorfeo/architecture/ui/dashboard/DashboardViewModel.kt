package io.github.athorfeo.architecture.ui.dashboard

import androidx.lifecycle.*
import io.github.athorfeo.architecture.repository.MovieRepository
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    movieRepository: MovieRepository
): ViewModel() {
    val message = MutableLiveData<String>()
    val movies = movieRepository.getFromDatabase().asLiveData()

    init {
        message.value = "Selecte theme"
    }

    fun setMessage(text: String){
        message.value = text
    }
}