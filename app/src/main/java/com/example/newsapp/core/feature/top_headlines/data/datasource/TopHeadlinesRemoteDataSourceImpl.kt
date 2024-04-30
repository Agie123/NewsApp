package com.example.newsapp.core.feature.top_headlines.data.datasource

import com.example.newsapp.BuildConfig
import com.example.newsapp.core.data.constants.COUNTRY_TAG_US
import com.example.newsapp.core.data.network.model.response.toTopHeadlines
import com.example.newsapp.core.data.network.retrofit.service.NewsService
import com.example.newsapp.core.feature.top_headlines.domain.datasource.TopHeadlinesRemoteDataSource
import com.example.newsapp.core.feature.top_headlines.domain.model.TopHeadlines
import com.example.newsapp.core.utils.EmptyResponseBodyException
import com.example.newsapp.core.utils.UnknownApiErrorException
import javax.inject.Inject

class TopHeadlinesRemoteDataSourceImpl @Inject constructor(
    private val newsService: NewsService
) : TopHeadlinesRemoteDataSource {
    override suspend fun getTopHeadlines(): TopHeadlines {
        val response = newsService.getTopHeadlines(
            country = COUNTRY_TAG_US,
            apiKey = BuildConfig.API_KEY
        )

        if (!response.isSuccessful) {
            throw UnknownApiErrorException(statusCode = response.code())
        }

        return response.body()?.toTopHeadlines() ?: throw EmptyResponseBodyException()

    }
}