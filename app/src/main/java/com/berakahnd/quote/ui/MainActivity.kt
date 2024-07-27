package com.berakahnd.quote.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.berakahnd.quote.QuoteWorker
import com.berakahnd.quote.ui.screen.AppNavHost
import com.berakahnd.quote.ui.screen.MainScreen
import com.berakahnd.quote.ui.theme.QuoteTheme
import com.berakahnd.quote.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initPeriodicWorker()

        setContent {
            QuoteTheme {
                val navController = rememberNavController()
                AppNavHost(
                    viewModel = viewModel,
                    navController = navController,
                )
            }
        }
    }

    private fun initPeriodicWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest =
            PeriodicWorkRequestBuilder<QuoteWorker>(15, TimeUnit.MINUTES)
                .setBackoffCriteria(
                    backoffPolicy = BackoffPolicy.LINEAR,
                    duration = Duration.ofSeconds(15)
                )
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "QUOTE_WORKER",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
    }
}