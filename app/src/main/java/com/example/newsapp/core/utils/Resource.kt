package com.example.newsapp.core.utils

sealed class Resource<out T> {

    // Represents a successful data request with the data.
    data class Success<out T>(val data: T) : Resource<T>()

    // Represents a loading state.
    data object Loading : Resource<Nothing>()

    // Represents an error state with a message.
    data class Error(val message: String) : Resource<Nothing>()
}