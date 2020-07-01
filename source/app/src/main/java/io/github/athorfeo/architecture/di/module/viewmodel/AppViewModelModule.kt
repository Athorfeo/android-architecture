package io.github.athorfeo.architecture.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.github.athorfeo.architecture.di.annotation.ViewModelKey
import io.github.athorfeo.architecture.ui.dashboard.DashboardViewModel
import io.github.athorfeo.architecture.viewmodel.AppViewModelFactory
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun dashboardViewModel(viewModel: DashboardViewModel): ViewModel
}