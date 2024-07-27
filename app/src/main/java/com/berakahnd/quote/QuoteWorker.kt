package com.berakahnd.quote

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.berakahnd.quote.data.repository.QuoteRepository
import com.berakahnd.quote.util.QuoteNotification
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay

@HiltWorker
class QuoteWorker @AssistedInject constructor(
    @Assisted context : Context,
    @Assisted params : WorkerParameters,
    private val repository : QuoteRepository
) : CoroutineWorker(context,params){

    override suspend fun doWork(): Result {
        return try {
            Log.i("BIBLE","BIBLE SUCCES")
            if(repository.getQuoteCount() > 0){
                repository.quoteDataFromWorker()

                val notification = QuoteNotification(context = applicationContext)
                notification.createNotificationChannel()
                val quoteItem = repository.getLastQuoteItem()
                notification.sendNotification(quoteItem.reference, quoteItem.text)
            }
            Result.success()
        }catch (e : Exception){
            Log.i("BIBLE","${e.message}")
            Result.retry()
        }
    }
}

