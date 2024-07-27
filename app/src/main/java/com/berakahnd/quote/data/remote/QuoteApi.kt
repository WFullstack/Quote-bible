package com.berakahnd.quote.data.remote

import com.berakahnd.quote.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {
    @GET("/?random=verse")
    suspend fun getQuote() : Response<QuoteModel>
}