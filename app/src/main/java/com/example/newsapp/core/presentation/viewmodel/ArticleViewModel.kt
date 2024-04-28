package com.example.newsapp.core.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.core.data.network.model.response.Article

class ArticleViewModel : ViewModel() {
    val article = MutableLiveData<Article>()
}