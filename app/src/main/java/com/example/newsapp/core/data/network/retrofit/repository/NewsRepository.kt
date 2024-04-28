package com.example.newsapp.core.data.network.retrofit.repository

import com.example.newsapp.BuildConfig
import com.example.newsapp.core.data.network.model.response.NewsResponse
import com.example.newsapp.core.data.network.retrofit.service.NewsService
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService) {
    suspend fun getTopHeadlines(country: String): Response<NewsResponse> {
        return newsService.getTopHeadlines(
            country = country,
            apiKey = BuildConfig.API_KEY
        )
    }
}