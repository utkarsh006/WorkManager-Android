package com.example.quoteswork.data.remote

import com.example.quoteswork.data.Constants.API_ENDPOINT
import com.example.quoteswork.data.model.QuoteDTO
import retrofit2.http.GET

interface ApiService {
    @GET(API_ENDPOINT)
    suspend fun getQuotes(): QuoteDTO
}