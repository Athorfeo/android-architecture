package io.github.athorfeo.architecture.ui.item.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import io.github.athorfeo.architecture.R
import io.github.athorfeo.architecture.databinding.FragmentSearchBinding
import io.github.athorfeo.architecture.ui.base.fragment.BaseFragment
import timber.log.Timber

class SearchFragment: BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_search, null, false)
        initializeFragment(viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clickListener = this
        binding.mViewModel = viewModel

        val adapter = ItemSearchAdapter(this)
        binding.recycler.adapter = adapter

        viewModel.searchResults.observe(viewLifecycleOwner, Observer {
            Timber.tag("AthorArch").d(it.toString())
            adapter.submitList(it)
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button_search -> {
                viewModel.search()
            }

            R.id.item_content -> {
                (v.tag as? String)?.let{
                    val direction = SearchFragmentDirections.toDetailFragment(it)
                    findNavController().navigate(direction)
                }
            }
        }
    }
}