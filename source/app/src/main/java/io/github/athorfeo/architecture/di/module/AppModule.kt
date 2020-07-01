package io.github.athorfeo.architecture.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.athorfeo.architecture.database.dao.MovieDao
import io.github.athorfeo.architecture.di.module.viewmodel.ViewModelModule
import javax.inject.Singleton

@Module(includes = [
    ViewModelModule::class,
    DatabaseModule::class
])
class AppModule{
    @Provides
    @Singleton
    fun applicationContext(application: Application): Context = application.applicationContext
}