package com.shaun.newsbreeze.models

data class NewsArticles(

    var status: String="",
    var totalResults: Int=1,
    var articles: ArrayList<Article> = arrayListOf()

)
