package com.example.quoteswork.data.repository

import com.example.quoteswork.domain.model.Quote
import com.example.quoteswork.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow

class QuoteRepoImpl : QuoteRepository {
    override fun getQuotes() {
        TODO("Not yet implemented")
    }

    override fun getAllQuotes(): Flow<List<Quote>> {
        TODO("Not yet implemented")
    }

    override fun setPeriodicWorkRequest() {
        TODO("Not yet implemented")
    }
}