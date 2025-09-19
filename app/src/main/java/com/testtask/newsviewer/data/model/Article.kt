package com.testtask.newsviewer.data.model

data class Article(
    val id: Long = nextId(),
    val source: Source? = null,
    val author: String? = null,
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = "",
){
    companion object {
        private var currentId = 0L
        private fun nextId() = currentId++
    }
}