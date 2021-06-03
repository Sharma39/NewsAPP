package com.example.newsappcc.model

data class Favourite(val userId: String, val postId: String, val title: String, val publishAt: String, val url:String){
    constructor(): this("", "", "","","")
}