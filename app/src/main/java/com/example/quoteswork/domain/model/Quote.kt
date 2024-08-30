package com.example.quoteswork.domain.model

data class Quote(
    val author: String,
    val id: Int,
    val quote: String,
    val workType: String = "",
    val time: Long = System.currentTimeMillis()
)