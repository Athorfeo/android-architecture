package io.github.athorfeo.architecture.ui.item.search

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import io.github.athorfeo.architecture.model.Item
import io.github.athorfeo.architecture.model.Resource
import io.github.athorfeo.architecture.model.SearchItem
import io.github.athorfeo.architecture.model.Status
import io.github.athorfeo.architecture.repository.ItemsRepository
import io.github.athorfeo.architecture.testing.OpenForTesting
import io.github.athorfeo.architecture.ui.base.viewmodel.BaseViewModel
import javax.inject.Inject

@OpenForTesting
class SearchViewModel @Inject constructor(
    private val itemsRepository: ItemsRepository
): BaseViewModel() {
    val searchQuery = ObservableField<String>()

    private val _searchResults = MediatorLiveData<List<SearchItem>>()
    val searchResults: LiveData<List<SearchItem>> = _searchResults

    fun search(){
        val liveData = itemsRepository.search(searchQuery.get() ?: "").asLiveData()

        _searchResults.addSource(
            liveData
        ){
            when(it.status){
                Status.LOADING -> {
                    setLoading(true)
                }
                Status.ERROR -> {
                    setLoading(false)
                    setError(it.code, it.message)
                }
                Status.SUCCESS -> {
                    setLoading(false)
                    _searchResults.removeSource(liveData)
                    _searchResults.value = it.data
                }
            }
        }
    }

    fun getItem(id: String): LiveData<Resource<Item>> = itemsRepository.searchById(id).asLiveData()
}