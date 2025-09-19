package com.testtask.newsviewer.data

import com.testtask.newsviewer.data.model.Article
import com.testtask.newsviewer.data.model.Source
import com.testtask.newsviewer.domain.ArticlesDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticlesRepository @Inject constructor(private val dataSource: ArticlesDataSource) {
    private val articles = MutableStateFlow<List<Article>>(emptyList())
    suspend fun getArticles(sources: List<Source>): List<Article> {
        val articles = dataSource.getArticles(sources)
        this.articles.value = articles
        return articles
    }

    fun getArticle(id: Long): Article = articles.value.find { it.id == id } ?: Article() // TODO handle not found
}