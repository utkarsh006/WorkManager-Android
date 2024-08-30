package com.example.quoteswork.domain.repository

import com.example.quoteswork.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun getQuotes()

    fun getAllQuotes(): Flow<List<Quote>>

    fun setPeriodicWorkRequest()
}