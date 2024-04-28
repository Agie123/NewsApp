package com.example.newsapp.core.utils

import com.example.newsapp.core.data.network.model.response.Article

interface OnArticleClickListener {
    fun onArticleClick(article: Article)
}