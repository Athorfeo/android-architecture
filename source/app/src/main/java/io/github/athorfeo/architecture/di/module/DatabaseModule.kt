package io.github.athorfeo.architecture.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.github.athorfeo.architecture.database.AppDatabase
import io.github.athorfeo.architecture.database.dao.MovieDao
import javax.inject.Singleton

@Module
class DatabaseModule{
    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "app-database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePriceStockDao(database: AppDatabase): MovieDao = database.movieDao()
}