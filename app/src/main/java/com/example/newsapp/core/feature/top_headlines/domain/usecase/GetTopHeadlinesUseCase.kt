package com.example.newsapp.core.feature.top_headlines.domain.usecase

import com.example.newsapp.core.feature.top_headlines.domain.datasource.TopHeadlinesRemoteDataSource
import com.example.newsapp.core.feature.top_headlines.domain.model.TopHeadlines
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val topHeadlinesRemoteDataSource: TopHeadlinesRemoteDataSource
) {
    private var headlinesCache: TopHeadlines? = null
    val hasCache: Boolean
        get() = headlinesCache != null

    suspend operator fun invoke(): TopHeadlines {
        return headlinesCache ?: runCatching {
            topHeadlinesRemoteDataSource.getTopHeadlines()
        }.fold(
            onSuccess = { topHeadlines ->
                headlinesCache = topHeadlines

                topHeadlines
            },

            onFailure = {
                throw it
            }
        )
    }
}