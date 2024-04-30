package com.example.newsapp.core.feature.article_details.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.core.feature.article_details.domain.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor() : ViewModel() {
    val currentArticle = MutableLiveData<Article>()
}