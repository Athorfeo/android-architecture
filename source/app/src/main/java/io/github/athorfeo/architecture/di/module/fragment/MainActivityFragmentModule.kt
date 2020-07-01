package io.github.athorfeo.architecture.di.module.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.athorfeo.architecture.ui.dashboard.DashboardFragment

@Module
abstract class MainActivityFragmentModule {
    @ContributesAndroidInjector
    abstract fun dashboardFragment(): DashboardFragment
}