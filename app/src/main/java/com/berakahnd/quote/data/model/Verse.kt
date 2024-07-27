package com.berakahnd.quote.data.model

data class Verse(
    val book_id: String = "",
    val book_name: String = "",
    val chapter: Int = 0,
    val text: String = "",
    val verse: Int = 0
)