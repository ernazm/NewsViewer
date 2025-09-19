package com.testtask.newsviewer.domain

import com.testtask.newsviewer.data.model.Article
import com.testtask.newsviewer.data.model.Source

interface ArticlesDataSource {
    suspend fun getArticles(sources: List<Source>): List<Article>
}