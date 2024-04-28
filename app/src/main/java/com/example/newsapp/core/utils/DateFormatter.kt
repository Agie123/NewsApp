package com.example.newsapp.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateFormatter {
    fun formatDateTime(dateTime: String): String {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

        val date: Date = inputDateFormat.parse(dateTime) ?: return ""

        val outputDateFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())

        return outputDateFormat.format(date)
    }
}