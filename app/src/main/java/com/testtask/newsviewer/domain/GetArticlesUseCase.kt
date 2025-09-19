package com.testtask.newsviewer.domain

import com.testtask.newsviewer.data.model.Article
import com.testtask.newsviewer.data.ArticlesRepository
import com.testtask.newsviewer.data.model.Source
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val articlesRepository: ArticlesRepository) {

    suspend operator fun invoke(sources: List<Source>): List<Article> {
        return articlesRepository.getArticles(sources)
    }
}