package io.github.athorfeo.architecture.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.athorfeo.architecture.BuildConfig
import io.github.athorfeo.architecture.database.dao.MovieDao
import io.github.athorfeo.architecture.di.module.viewmodel.ViewModelModule
import io.github.athorfeo.architecture.network.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [
    ViewModelModule::class,
    DatabaseModule::class
])
class AppModule{
    @Provides
    @Singleton
    fun applicationContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideApiService(): Api {
        val client = OkHttpClient.Builder()
            .apply {
                if(BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            this.level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                }
            }
            .build()
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.mercadolibre.com/")
            .build()
            .create(Api::class.java)
    }
}