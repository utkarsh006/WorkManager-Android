package com.example.quoteswork.domain.usecases

data class QuoteUseCases(
    val getQuote: GetQuote,
    val getAllQuotesFromDb: GetAllQuotesFromDb,
    val setUpPeriodicRequest: SetUpPeriodicRequest
)