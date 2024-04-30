package com.example.newsapp.core.feature.top_headlines.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.core.feature.top_headlines.domain.model.TopHeadlines
import com.example.newsapp.core.feature.top_headlines.domain.usecase.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadLinesViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {
    private val _topHeadlines = MutableLiveData<TopHeadlines>()
    val topHeadlines: LiveData<TopHeadlines> get() = _topHeadlines

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun fetchTopHeadlines() {
        viewModelScope.launch {
            if (getTopHeadlinesUseCase.hasCache) return@launch

            _isLoading.value = true

            try {
                _topHeadlines.value = getTopHeadlinesUseCase()
            } finally {
                _isLoading.value = false
            }
        }
    }
}