package com.testtask.newsviewer.ui.article.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testtask.newsviewer.domain.GetArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getArticleUseCase: GetArticleUseCase,
) : ViewModel() {
    private val _articleId = MutableStateFlow<Long>(savedStateHandle["id"]!!)
    val article = _articleId
        .map {
            getArticleUseCase(it)
        }
        .map { article ->
            ArticleDetails(
                title = article.title,
                content = article.content,
                author = article.author?.takeUnless { it.isBlank() }?.let { "By $it" },
            )
        }
        .stateIn(viewModelScope, started = WhileSubscribed(5_000), initialValue = ArticleDetails())

    data class ArticleDetails(
        val title: String = "",
        val content: String = "",
        val author: String? = null,
    )
}