package com.example.newsappcc.model

import java.io.Serializable

data class Articles (

    var source: Source,
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String
) : Serializable