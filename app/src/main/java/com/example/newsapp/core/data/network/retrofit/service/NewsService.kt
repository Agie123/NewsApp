package com.example.newsapp.core.data.network.retrofit.service

import com.example.newsapp.core.data.network.model.response.TopHeadlinesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<TopHeadlinesResponse>
}