package com.example.newsappcc.model

data class Headlines (
    var status: String,
    var totalResults: String,
    var articles: List<Articles>
)