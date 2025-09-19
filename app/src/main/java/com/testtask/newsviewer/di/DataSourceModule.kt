package com.testtask.newsviewer.di

import com.testtask.newsviewer.data.remote.ArticlesRemoteDataSource
import com.testtask.newsviewer.domain.ArticlesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindArticlesDataSource(dataSourceImpl: ArticlesRemoteDataSource): ArticlesDataSource
}