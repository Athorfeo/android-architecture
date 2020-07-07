package io.github.athorfeo.architecture.ui.item.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.athorfeo.architecture.R
import io.github.athorfeo.architecture.databinding.ItemSearchBinding
import io.github.athorfeo.architecture.model.SearchItem


class ItemSearchAdapter(private val listener: View.OnClickListener): ListAdapter<SearchItem, ItemSearchAdapter.ViewHolder>(DiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_search, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { item ->
            with(holder) {
                bind(item, listener)
            }
        }
    }

    class ViewHolder (private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: SearchItem, viewListener: View.OnClickListener){
            with(binding) {
                itemContent.tag = item.id
                viewModel = ItemSearchViewModel(item)
                clickListener = viewListener
                executePendingBindings()
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem == newItem
        }
    }
}