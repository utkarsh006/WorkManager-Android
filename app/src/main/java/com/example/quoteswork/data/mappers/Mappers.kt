package com.example.quoteswork.data.mappers

import com.example.quoteswork.data.model.QuoteDTO
import com.example.quoteswork.domain.model.Quote

fun QuoteDTO.toDomain(workType: String): Quote {
    return Quote(author, id, quote, workType)
}