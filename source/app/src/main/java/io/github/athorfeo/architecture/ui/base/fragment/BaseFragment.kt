package io.github.athorfeo.architecture.ui.base.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.athorfeo.architecture.di.annotation.Injectable
import javax.inject.Inject

open class BaseFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}