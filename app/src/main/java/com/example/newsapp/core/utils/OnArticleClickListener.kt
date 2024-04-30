package com.example.newsapp.core.utils

import com.example.newsapp.core.feature.article_details.domain.Article

interface OnArticleClickListener {
    fun onArticleClick(article: Article)
}