package com.testtask.newsviewer.data.remote

import com.testtask.newsviewer.data.model.ArticlesList
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/everything")
    suspend fun getArticles(
        @Query("sources") sources: String
    ): ArticlesList
}
