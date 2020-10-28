package io.github.athorfeo.architecture.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.athorfeo.architecture.ui.MainActivity
import io.github.athorfeo.architecture.di.module.fragment.MainActivityFragmentModule

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}