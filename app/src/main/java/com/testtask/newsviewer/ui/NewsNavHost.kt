package com.testtask.newsviewer.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.testtask.newsviewer.ui.article.details.ArticleDetailsScreen
import com.testtask.newsviewer.ui.article.ArticlesListScreen
import com.testtask.newsviewer.ui.article.ArticlesListViewModel

@Composable
fun NewsNavHost(articlesListViewModel: ArticlesListViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.ArticlesList.route) {
        composable(route = Screen.ArticlesList.route) {
            ArticlesListScreen(navController, articlesListViewModel)
        }
        composable(
            route = Screen.ArticleDetails.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            )
        ) {
            ArticleDetailsScreen(navController)
        }
    }
}
