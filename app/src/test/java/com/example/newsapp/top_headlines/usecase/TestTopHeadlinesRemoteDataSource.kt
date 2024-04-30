package com.example.newsapp.top_headlines.usecase

import com.example.newsapp.core.data.constants.STATUS_OK
import com.example.newsapp.core.feature.top_headlines.domain.datasource.TopHeadlinesRemoteDataSource
import com.example.newsapp.core.feature.top_headlines.domain.model.TopHeadlines

class TestTopHeadlinesRemoteDataSource : TopHeadlinesRemoteDataSource {
    private val articles = listOf<Article>()
    private val topHeadlines = TopHeadlines(
        status = STATUS_OK,
        totalResults = 100,
        articles = articles
    )

    override suspend fun getTopHeadlines(): TopHeadlines {
        return topHeadlines
    }
}