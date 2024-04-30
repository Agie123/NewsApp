package com.example.newsapp.core.feature.top_headlines.domain.datasource

import com.example.newsapp.core.feature.top_headlines.domain.model.TopHeadlines

interface TopHeadlinesRemoteDataSource {
    suspend fun getTopHeadlines(): TopHeadlines
}