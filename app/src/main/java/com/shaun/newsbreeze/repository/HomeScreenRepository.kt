package com.shaun.newsbreeze.repository

import androidx.lifecycle.MutableLiveData
import com.shaun.newsbreeze.models.NewsArticles

interface HomeScreenRepository {

    var searchFailed:MutableLiveData<Boolean>
   suspend fun getHeadlines(): NewsArticles
    suspend fun searchArticle(query: String): NewsArticles
}