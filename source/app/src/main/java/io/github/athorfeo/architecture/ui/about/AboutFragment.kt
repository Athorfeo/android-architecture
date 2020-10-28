package io.github.athorfeo.architecture.ui.about

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import io.github.athorfeo.architecture.R
import io.github.athorfeo.architecture.databinding.FragmentAboutBinding
import io.github.athorfeo.architecture.ui.base.fragment.BaseFragment

class AboutFragment: BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding =  DataBindingUtil.inflate(layoutInflater, R.layout.fragment_about, null, false)

        with(binding){
            lifecycleOwner = viewLifecycleOwner
            clickListener = this@AboutFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {
        when(v?.id){
        }
    }
}