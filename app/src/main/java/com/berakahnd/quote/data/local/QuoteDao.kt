package com.berakahnd.quote.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuoteItem(quoteItem: QuoteItem)

    @Query("SELECT * FROM QuoteItem ORDER BY id DESC LIMIT 1")
    suspend fun getLastQuoteItem() : QuoteItem

    @Query("SELECT * FROM QuoteItem ORDER BY reference ASC ")
    fun geAllQuoteItem() : Flow<List<QuoteItem>>

    @Delete
    suspend fun deleteQuoteItem(quoteItem: QuoteItem)

    @Query("SELECT COUNT(*) FROM QuoteItem")
    suspend fun getQuoteCount(): Int
}