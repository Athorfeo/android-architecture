package io.github.athorfeo.architecture.di.module.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.athorfeo.architecture.ui.base.fragment.BaseFragment
import io.github.athorfeo.architecture.ui.item.detail.DetailFragment
import io.github.athorfeo.architecture.ui.item.search.SearchFragment

@Module
abstract class MainActivityFragmentModule {
    @ContributesAndroidInjector
    abstract fun baseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun searchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun detailFragment(): DetailFragment
}