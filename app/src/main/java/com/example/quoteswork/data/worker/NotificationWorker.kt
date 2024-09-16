package com.example.quoteswork.data.worker

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.QuotesWork.R
import com.example.quoteswork.CHANNEL
import com.example.quoteswork.domain.model.Quote
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlin.text.Typography.quote

const val QUOTE = "quote"

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val quoteJson = workerParams.inputData?.getString(QUOTE)
            val quote = Gson().fromJson(quoteJson, Quote::class.java)

            if (ContextCompat.checkSelfPermission(
                    context,
                    POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val notification = NotificationCompat.Builder(context, CHANNEL)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Quote")
                    .setContentText(quote.quote.plus("${quote.author}"))
                    .build()

                NotificationManagerCompat.from(context)
                    .notify(1, notification)
            }
        }

        return Result.success()
    }
}