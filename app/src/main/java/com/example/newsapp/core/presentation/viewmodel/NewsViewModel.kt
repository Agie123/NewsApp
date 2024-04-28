package com.example.newsapp.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.core.data.network.model.response.NewsResponse
import com.example.newsapp.core.data.network.retrofit.repository.NewsRepository
import com.example.newsapp.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private val _topHeadlines = MutableLiveData<Resource<NewsResponse>>()
    val topHeadlines: LiveData<Resource<NewsResponse>> get() = _topHeadlines

    fun fetchTopHeadlines(country: String) {
        viewModelScope.launch {
            _topHeadlines.value = Resource.Loading

            try {
                val response = newsRepository.getTopHeadlines(country)

                if (response.isSuccessful) {
                    _topHeadlines.value = Resource.Success(response.body()!!)
                } else {
                    _topHeadlines.value = Resource.Error("Error fetching top headlines")
                }
            } catch (e: Exception) {
                _topHeadlines.value = Resource.Error(e.message ?: "Unknown error")
            }
        }
    }
}