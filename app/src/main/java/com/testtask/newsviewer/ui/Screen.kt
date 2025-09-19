package com.testtask.newsviewer.ui

sealed class Screen(open val route: String) {
    object ArticlesList : Screen("articles_list")
    object ArticleDetails : Screen("article_details/{id}")
}