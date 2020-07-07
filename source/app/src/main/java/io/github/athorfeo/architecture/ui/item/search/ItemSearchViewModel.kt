package io.github.athorfeo.architecture.ui.item.search

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import io.github.athorfeo.architecture.model.SearchItem

class ItemSearchViewModel(data: SearchItem?): ViewModel(){
    private val item = checkNotNull(data)

    val id = ObservableField(item.id)
    val price =  ObservableField(item.price)
    val soldQuantity =  ObservableField(item.soldQuantity)
    val thumbnail =  ObservableField(item.thumbnail)
    val title =  ObservableField(item.title)

}