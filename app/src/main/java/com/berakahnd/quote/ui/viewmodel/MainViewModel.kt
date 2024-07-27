package com.berakahnd.quote.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berakahnd.quote.data.local.QuoteItem
import com.berakahnd.quote.data.repository.QuoteRepository
import com.berakahnd.quote.util.QuoteResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel() {
    val uistate = mutableStateOf(MainUistate())

    init{
        viewModelScope.launch {
            if(getQuoteCount() > 0){
                getLastQuoteItem()
                geAllQuoteItem()
            }else{
                getRemoteQuote()
            }
        }
    }
    fun getRemoteQuote(){
        viewModelScope.launch {
            repository.getRemoteQuote().collect{ result ->
                when(result){
                    is QuoteResult.Error -> {
                        uistate.value = uistate.value.copy(error = result.errorMessage, isLoading = false)
                    }
                    QuoteResult.Loading -> {
                        uistate.value = uistate.value.copy(isLoading = true)
                    }
                    is QuoteResult.Success -> {
                        try{
                            val quoteItem = QuoteItem(
                                reference = result.data.reference,
                                text = result.data.text
                            )
                            uistate.value = uistate.value.copy( quoteItem = quoteItem, error = "",
                                isLoading = false
                            )
                            insert(quoteItem)
                        }catch (e: Exception){
                            Log.i("BIBLE","$e")
                        }
                    }
                }
            }
        }
    }
    private fun geAllQuoteItem(){
        viewModelScope.launch {
            repository.geAllQuoteItem().collect{ result ->
                uistate.value = uistate.value.copy(listQuoteItem = result)
            }
        }
    }
    private fun getLastQuoteItem(){
        viewModelScope.launch {
            val quoteItem =  repository.getLastQuoteItem()
            uistate.value = uistate.value.copy(
                quoteItem = quoteItem
            )
        }
    }
    fun insert(quoteItem : QuoteItem){
        viewModelScope.launch {
            repository.insert(quoteItem)
        }
    }
    fun delete(quoteItem : QuoteItem){
        viewModelScope.launch {
            repository.delete(quoteItem)
        }
    }
    suspend fun getQuoteCount() = repository.getQuoteCount()

}