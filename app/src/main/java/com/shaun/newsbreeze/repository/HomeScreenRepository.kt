package com.shaun.newsbreeze.repository

import androidx.lifecycle.MutableLiveData
import com.shaun.newsbreeze.models.NewsArticles

interface HomeScreenRepository {

    fun getHeadlines():MutableLiveData<NewsArticles>
    fun searchArticle(query: String): MutableLiveData<NewsArticles>
}