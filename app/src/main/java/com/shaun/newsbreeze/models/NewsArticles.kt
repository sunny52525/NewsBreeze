package com.shaun.newsbreeze.models

data class NewsArticles(

    var status: String,
    var totalResults: Int,
    var articles: ArrayList<Article>

)
