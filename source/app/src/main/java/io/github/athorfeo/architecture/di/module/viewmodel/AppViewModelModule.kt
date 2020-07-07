package io.github.athorfeo.architecture.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.multibindings.IntoMap
import io.github.athorfeo.architecture.di.annotation.ViewModelKey
import io.github.athorfeo.architecture.ui.item.search.SearchViewModel
import io.github.athorfeo.architecture.ui.base.viewmodel.AppViewModelFactory

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    @Reusable
    abstract fun dashboardViewModel(viewModel: SearchViewModel): ViewModel
}