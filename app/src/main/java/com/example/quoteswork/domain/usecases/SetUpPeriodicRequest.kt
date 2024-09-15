package com.example.quoteswork.domain.usecases

import com.example.quoteswork.domain.repository.QuoteRepository
import javax.inject.Inject

class SetUpPeriodicRequest @Inject constructor(private val quoteRepository: QuoteRepository) {

    operator fun invoke() = quoteRepository.setPeriodicWorkRequest()
}