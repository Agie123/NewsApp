package com.example.newsapp.core.data.network.model.response

import com.example.newsapp.core.feature.article_details.domain.Article
import com.example.newsapp.core.feature.top_headlines.domain.model.TopHeadlines

data class TopHeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

fun TopHeadlinesResponse.toTopHeadlines() = TopHeadlines(
    status = status,
    totalResults = totalResults,
    articles = articles
)