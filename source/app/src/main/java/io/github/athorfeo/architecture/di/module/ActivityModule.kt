package io.github.athorfeo.architecture.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.athorfeo.architecture.ui.activity.MainActivity
import io.github.athorfeo.architecture.di.module.fragment.MainActivityFragmentModule
import io.github.athorfeo.architecture.ui.activity.BaseActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}