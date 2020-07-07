package io.github.athorfeo.architecture.ui.item.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import io.github.athorfeo.architecture.R
import io.github.athorfeo.architecture.databinding.FragmentDetailBinding
import io.github.athorfeo.architecture.ui.fragment.BaseFragment
import io.github.athorfeo.architecture.ui.item.search.SearchViewModel
import timber.log.Timber

class DetailFragment: BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: SearchViewModel by viewModels { viewModelFactory }
    val args: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clickListener = this

        viewModel.getItem(args.id).observe(viewLifecycleOwner, Observer {
            Timber.tag("AthorArch").d(it.toString())
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button_search -> {
            }
        }
    }
}