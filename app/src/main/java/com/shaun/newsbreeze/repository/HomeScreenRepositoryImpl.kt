package com.shaun.newsbreeze.repository

import androidx.lifecycle.MutableLiveData
import com.shaun.newsbreeze.models.NewsArticles
import com.shaun.newsbreeze.network.NewsApiService
import retrofit2.Call
import retrofit2.Response

class HomeScreenRepositoryImpl(
    val apiKey: String, val retrofit: NewsApiService,
) :

    HomeScreenRepository {

    override var searchFailed = MutableLiveData(false)

    override fun getHeadlines(): MutableLiveData<NewsArticles> {

        val result = MutableLiveData<NewsArticles>()
        retrofit.getTopHeadlines(apiKey = apiKey)
            .enqueue(object : retrofit2.Callback<NewsArticles> {
                override fun onResponse(
                    call: Call<NewsArticles>,
                    response: Response<NewsArticles>
                ) {
                    if (response.isSuccessful) {

                        if (response.body()?.articles?.isEmpty() == true)
                            searchFailed.postValue(true)
                        result.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<NewsArticles>, t: Throwable) {
                    searchFailed.postValue(true)
                }

            })
        return result

    }

    override fun searchArticle(query: String): MutableLiveData<NewsArticles> {

        val result = MutableLiveData<NewsArticles>()
        retrofit.searchArticles(query = query, apiKey = apiKey)
            .enqueue(object : retrofit2.Callback<NewsArticles> {
                override fun onResponse(
                    call: Call<NewsArticles>,
                    response: Response<NewsArticles>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()?.articles?.isEmpty() == true)
                            searchFailed.postValue(true)
                        result.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<NewsArticles>, t: Throwable) {
                    searchFailed.postValue(true)
                }

            })
        return result
    }

    companion object {
        private const val TAG = "HomeScreenRepositoryImp"
    }
}