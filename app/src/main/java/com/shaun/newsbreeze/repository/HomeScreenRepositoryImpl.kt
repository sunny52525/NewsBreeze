package com.shaun.newsbreeze.repository

import androidx.lifecycle.MutableLiveData
import com.shaun.newsbreeze.models.NewsArticles
import com.shaun.newsbreeze.network.NewsApiService

class HomeScreenRepositoryImpl(
    val apiKey: String, val retrofit: NewsApiService,
) : HomeScreenRepository {

    override var searchFailed = MutableLiveData(false)

    override suspend fun getHeadlines(): NewsArticles {

        return retrofit.getTopHeadlines(apiKey = apiKey)

    }

    override suspend fun searchArticle(query: String): NewsArticles {
        return retrofit.searchArticles(query = query, apiKey = apiKey)
    }

    companion object {
        private const val TAG = "HomeScreenRepositoryImp"
    }
}