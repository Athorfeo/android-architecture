package io.github.athorfeo.architecture.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import io.github.athorfeo.architecture.R
import io.github.athorfeo.architecture.databinding.FragmentDashboardBinding
import io.github.athorfeo.architecture.ui.fragment.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

class DashboardFragment: BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: DashboardViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding =  DataBindingUtil.inflate(layoutInflater, R.layout.fragment_dashboard, null, false)

        with(binding){
            clickListener = this@DashboardFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            Timber.tag("ArchApp").i(it.toString())
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button_light_theme -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            R.id.button_dark_theme -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }
}