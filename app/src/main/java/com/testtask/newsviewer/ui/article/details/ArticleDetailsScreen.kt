package com.testtask.newsviewer.ui.article.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.testtask.newsviewer.ui.article.details.ArticleDetailsViewModel.ArticleDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailsScreen(
    navController: NavController,
    viewModel: ArticleDetailsViewModel = hiltViewModel()
) {
    val article by viewModel.article.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(article.title) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Up")
                    }
                }
            )
        }
    ) { innerPadding ->
        ArticleDetailsView(Modifier.padding(innerPadding), article)
    }
}

@Composable
fun ArticleDetailsView(modifier: Modifier = Modifier, article: ArticleDetails) = Column(modifier) {
    article.author?.let { author ->
        Text(
            text = author,
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(16.dp)
        )
    }
    Text(
        text = article.content,
        fontSize = 16.sp,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
@Preview
fun ArticleDetailsViewPreview() = Box(modifier = Modifier.fillMaxSize()) {
    ArticleDetailsView(
        modifier = Modifier.fillMaxSize(),
        article = ArticleDetails(
            title = "Article Title",
            author = "By Author Name",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        )
    )
}

@Composable
@Preview
fun ArticleDetailsViewNoAuthorPreview() = Box(modifier = Modifier.fillMaxSize()) {
    ArticleDetailsView(
        modifier = Modifier.fillMaxSize(),
        article = ArticleDetails(
            title = "Article Title",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        )
    )
}