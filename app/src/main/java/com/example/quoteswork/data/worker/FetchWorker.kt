package com.example.quoteswork.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.quoteswork.data.local.QuoteDao
import com.example.quoteswork.data.mappers.toDomain
import com.example.quoteswork.data.remote.ApiService
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

const val ONE_TIME_WORK_REQUEST = "ONE_TIME_WORK_REQUEST"

// fetching quotes from backend and storing them in localDB.
// using Assisted means that we want to create an object of FetchWorker class during runtime.

@HiltWorker
class FetchWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val apiService: ApiService,
    private val quoteDao: QuoteDao
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val response = apiService.getQuotes().toDomain(ONE_TIME_WORK_REQUEST)
            quoteDao.insert(response)
            val data = Data.Builder()
                .putString(QUOTE, Gson().toJson(response)).build()
            Result.success(data)
        } catch (e: Exception) {
            Result.failure()
        }
    }
}