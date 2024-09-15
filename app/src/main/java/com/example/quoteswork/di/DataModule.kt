package com.example.quoteswork.di

import android.content.Context
import androidx.work.WorkManager
import com.example.quoteswork.data.Constants.BASE_URL
import com.example.quoteswork.data.local.QuoteDB
import com.example.quoteswork.data.local.QuoteDao
import com.example.quoteswork.data.remote.ApiService
import com.example.quoteswork.data.repository.QuoteRepoImpl
import com.example.quoteswork.domain.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): QuoteDB {
        return QuoteDB.getInstance(context)
    }

    @Provides
    fun provideDao(quoteDB: QuoteDB): QuoteDao {
        return quoteDB.getQuoteDao()
    }

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    fun provideQuoteRepository(workManager: WorkManager, quoteDao: QuoteDao): QuoteRepository {
        return QuoteRepoImpl(workManager, quoteDao)
    }
}