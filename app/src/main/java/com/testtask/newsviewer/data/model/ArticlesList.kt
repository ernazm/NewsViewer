package com.testtask.newsviewer.data.model

data class ArticlesList(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)