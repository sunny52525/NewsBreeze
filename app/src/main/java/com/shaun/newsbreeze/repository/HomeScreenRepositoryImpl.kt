package com.shaun.newsbreeze.repository

import androidx.lifecycle.MutableLiveData
import com.shaun.newsbreeze.models.NewsArticles
import com.shaun.newsbreeze.network.NewsApiService
import retrofit2.Call
import retrofit2.Response

class HomeScreenRepositoryImpl(val apiKey: String, val retrofit: NewsApiService) :
    HomeScreenRepository {


    override fun getHeadlines(): MutableLiveData<NewsArticles> {

        val result = MutableLiveData<NewsArticles>()
        retrofit.getTopHeadlines(apiKey = apiKey)
            .enqueue(object : retrofit2.Callback<NewsArticles> {
                override fun onResponse(
                    call: Call<NewsArticles>,
                    response: Response<NewsArticles>
                ) {

                    if (response.isSuccessful) {

                    }
                }

                override fun onFailure(call: Call<NewsArticles>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return result

    }

    override fun searchArticle(query: String): MutableLiveData<NewsArticles> {
        TODO("Not yet implemented")
    }
}