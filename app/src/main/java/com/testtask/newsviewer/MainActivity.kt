package com.testtask.newsviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.testtask.newsviewer.ui.NewsNavHost
import com.testtask.newsviewer.ui.article.ArticlesListViewModel
import com.testtask.newsviewer.ui.theme.NewsViewerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val articlesListViewModel: ArticlesListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsViewerTheme {
                NewsNavHost(articlesListViewModel)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        articlesListViewModel.refresh()
    }
}
