package com.example.quoteswork.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteswork.domain.model.Quote
import com.example.quoteswork.domain.usecases.QuoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val quoteUseCases: QuoteUseCases
) : ViewModel() {

    val uiState = quoteUseCases.getAllQuotesFromDb.invoke()
        .map { UiState(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, UiState(emptyList()))

    init {
        quoteUseCases.setUpPeriodicRequest.invoke()
    }

    fun getQuote() = quoteUseCases.getQuote.invoke()
}

data class UiState(val data: List<Quote>)