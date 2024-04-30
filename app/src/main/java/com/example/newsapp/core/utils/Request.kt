package com.example.newsapp.core.utils

sealed class Request<out T> {

    // Represents a successful data request with the data.
    data class Success<out T>(val data: T) : Request<T>()

    // Represents a loading state.
    data object Loading : Request<Nothing>()

    // Represents an error state with a message.
    data class Error(val message: String) : Request<Nothing>()
}