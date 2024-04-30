package com.example.newsapp.core.feature.top_headlines.domain.model

import com.example.newsapp.core.feature.article_details.domain.Article

data class TopHeadlines(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
