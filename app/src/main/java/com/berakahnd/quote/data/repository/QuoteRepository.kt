package com.berakahnd.quote.data.repository

import android.app.Application
import android.util.Log
import com.berakahnd.quote.data.local.QuoteDao
import com.berakahnd.quote.data.local.QuoteItem
import com.berakahnd.quote.data.remote.QuoteApi
import com.berakahnd.quote.util.QuoteResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    val application: Application,
    private val api : QuoteApi,
    private val dao : QuoteDao
) {
    suspend fun getRemoteQuote() = flow{
        emit(QuoteResult.Loading)
        val data = api.getQuote().body()!!
        emit(QuoteResult.Success(data = data))
    }.catch { error ->
        emit(QuoteResult.Error(errorMessage = error.message!!))
    }
    suspend fun quoteDataFromWorker(){
        when(api.getQuote().isSuccessful){
            true -> {
                val data = api.getQuote().body()!!
                val reference = data.reference
                val text = data.text
                val quoteItem = QuoteItem(reference = reference, text =  text)

                insert(quoteItem)
                Log.i("BIBLE","$reference : $text")
            }
            false -> {
                Log.i("BIBLE","failure data")
            }
        }
    }
    suspend fun insert(quoteItem : QuoteItem) = dao.insertQuoteItem(quoteItem)
    suspend fun delete(quoteItem : QuoteItem) = dao.deleteQuoteItem(quoteItem)
    suspend fun getLastQuoteItem() = dao.getLastQuoteItem()
    fun geAllQuoteItem() = dao.geAllQuoteItem()
    suspend fun getQuoteCount() = dao.getQuoteCount()
}