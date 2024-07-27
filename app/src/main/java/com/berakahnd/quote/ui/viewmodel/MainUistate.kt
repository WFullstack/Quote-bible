package com.berakahnd.quote.ui.viewmodel

import com.berakahnd.quote.data.local.QuoteItem

data class MainUistate(
    val quoteItem: QuoteItem = QuoteItem(),
    val listQuoteItem: List<QuoteItem> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)
