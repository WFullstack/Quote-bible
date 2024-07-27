package com.berakahnd.quote.util

sealed class QuoteResult<out T> {
    data class Success<out T>(val data: T) : QuoteResult<T>()
    data class Error(val errorMessage: String) : QuoteResult<Nothing>()
    object Loading : QuoteResult<Nothing>()
}