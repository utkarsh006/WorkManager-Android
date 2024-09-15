package com.example.quoteswork.data.repository

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.quoteswork.data.local.QuoteDao
import com.example.quoteswork.data.worker.FetchWorker
import com.example.quoteswork.data.worker.PeriodicWorker
import com.example.quoteswork.domain.model.Quote
import com.example.quoteswork.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

class QuoteRepoImpl(
    private val workManager: WorkManager,
    private val quoteDao: QuoteDao
) : QuoteRepository {

    override fun getQuotes() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // if constraints are met, then only execute our work request
        val workRequest = OneTimeWorkRequestBuilder<FetchWorker>()
            .setConstraints(constraints)
            .build()

        workManager.enqueue(workRequest)
    }

    override fun getAllQuotes(): Flow<List<Quote>> = quoteDao.getAllQuotes()


    override fun setPeriodicWorkRequest() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // if constraints are met, then only execute our work request
        val workRequest =
            PeriodicWorkRequest.Builder(PeriodicWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        workManager.enqueueUniquePeriodicWork(
            "workName",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }
}