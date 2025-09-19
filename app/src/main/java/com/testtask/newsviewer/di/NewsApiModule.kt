package com.testtask.newsviewer.di

import com.testtask.newsviewer.data.remote.ApiKeyInterceptor
import com.testtask.newsviewer.data.remote.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NewsApiModule {
    @Provides
    fun provideNewsApi(): NewsApi {
        val apiKey = "7e622e2953024947914d113a4a717d5b" //TODO move to secure place
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(apiKey))
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    companion object {
        private const val BASE_URL = "https://newsapi.org/"
    }
}