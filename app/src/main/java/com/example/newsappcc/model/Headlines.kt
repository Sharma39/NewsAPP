package com.example.newsappcc.model

import com.example.newsappcc.model.Articles

data class Headlines (
    var status: String,
    var totalResults: String,
    var articles: List<Articles>
)