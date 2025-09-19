package com.testtask.newsviewer.domain

import com.testtask.newsviewer.data.ArticlesRepository
import com.testtask.newsviewer.data.model.Article
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(private val articlesRepository: ArticlesRepository) {

    operator fun invoke(id: Long): Article {
        return articlesRepository.getArticle(id)
    }
}