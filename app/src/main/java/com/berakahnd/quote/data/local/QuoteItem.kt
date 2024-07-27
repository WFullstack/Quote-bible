package com.berakahnd.quote.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuoteItem(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val reference : String = "",
    val text : String = ""
)
