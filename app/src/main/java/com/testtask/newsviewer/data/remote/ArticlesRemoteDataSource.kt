package com.testtask.newsviewer.data.remote

import com.testtask.newsviewer.data.model.Article
import com.testtask.newsviewer.data.model.Source
import com.testtask.newsviewer.domain.ArticlesDataSource
import javax.inject.Inject

class ArticlesRemoteDataSource @Inject constructor(private val newsApi: NewsApi) : ArticlesDataSource {
    override suspend fun getArticles(sources: List<Source>): List<Article> =
        newsApi.getArticles(sources.joinToString { it.id }).articles
}