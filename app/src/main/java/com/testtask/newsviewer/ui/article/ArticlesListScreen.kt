package com.testtask.newsviewer.ui.article

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.testtask.newsviewer.data.model.Article
import com.testtask.newsviewer.ui.article.ArticlesListViewModel.ArticleUiState
import com.testtask.newsviewer.ui.article.ArticlesListViewModel.ArticleUiState.Item

@Composable
fun ArticlesListScreen(
    navController: NavController,
    viewModel: ArticlesListViewModel = hiltViewModel(),
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val articles by viewModel.articles.collectAsStateWithLifecycle()
        ArticlesListView(
            modifier = Modifier.padding(innerPadding),
            articles = articles,
            interactor = object : ArticlesListScreenInteractor {
                override fun onArticleClick(article: Article) {
                    navController.navigate("article_details/${article.id}")
                    viewModel.onArticleClick(article)
                }
            }
        )
    }
}

@Composable
fun ArticlesListView(
    modifier: Modifier = Modifier,
    articles: List<ArticleUiState>,
    interactor: ArticlesListScreenInteractor
) {
    LazyColumn(modifier.fillMaxWidth()) {
        items(articles) { articleUiState ->
            when (articleUiState) {
                is ArticleUiState.Loading -> {
                    Box(Modifier.fillMaxWidth()) {
                        CircularProgressIndicator()
                    }
                }

                else -> {
                    ArticleListItem(
                        article = articleUiState,
                        modifier = Modifier.clickable {
                            articleUiState.article?.let { interactor.onArticleClick(it) }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ArticleListItem(modifier: Modifier = Modifier, article: ArticleUiState) = Column(modifier.padding(16.dp)) {
    Text(text = article.title)
    HorizontalDivider()
}

interface ArticlesListScreenInteractor {
    fun onArticleClick(article: Article) = Unit

    companion object {
        val Empty = object : ArticlesListScreenInteractor {}
    }
}

@Composable
@Preview
fun ArticlesListViewPreview() {
    ArticlesListView(
        modifier = Modifier.fillMaxSize(),
        articles = listOf(
            Item(title = "Title 1"),
            Item(title = "Title 2"),
            Item(title = "Title 3"),
        ),
        interactor = ArticlesListScreenInteractor.Empty
    )
}