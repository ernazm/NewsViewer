package com.testtask.newsviewer.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testtask.newsviewer.data.model.Article
import com.testtask.newsviewer.data.model.Source
import com.testtask.newsviewer.domain.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class ArticlesListViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel(), ArticlesListScreenInteractor {
    private val _articles = MutableStateFlow<List<ArticleUiState>>(emptyList())
    val articles = _articles.asStateFlow()

    override fun onArticleClick(article: Article) {
        // TODO track or handle article click if needed
    }

    fun refresh() {
        viewModelScope.launch {
            fetchArticles(listOf(Source(id = "bbc-news", name = "BBC News"))) // TODO get from repository or args
        }
    }

    private suspend fun fetchArticles(sources: List<Source>) {
        _articles.value = listOf(ArticleUiState.Loading) + _articles.value
        delay(1.seconds) // to see the loading state
        _articles.value = getArticlesUseCase(sources).map {
            ArticleUiState.Item(
                title = it.title,
                imageUrl = it.urlToImage,
                article = it
            )
        }
    }

    sealed interface ArticleUiState {
        data class Item(
            val title: String = "",
            val imageUrl: String? = null,
            val article: Article? = null,
        ) : ArticleUiState

        data object Loading : ArticleUiState
    }
}