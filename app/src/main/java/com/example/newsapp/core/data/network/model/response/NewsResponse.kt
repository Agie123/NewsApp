package com.example.newsapp.core.data.network.model.response

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)