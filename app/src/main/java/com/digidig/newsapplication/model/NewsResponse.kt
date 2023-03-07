package com.digidig.newsapplication.model

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)