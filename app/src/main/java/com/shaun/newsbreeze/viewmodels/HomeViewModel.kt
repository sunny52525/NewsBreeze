package com.shaun.newsbreeze.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaun.newsbreeze.localdatabase.ArticleLocal
import com.shaun.newsbreeze.models.NewsArticles
import com.shaun.newsbreeze.repository.HomeScreenRepository
import com.shaun.newsbreeze.utils.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeScreenRepository
) : ViewModel() {

    val searchStringLiveData = MutableLiveData("")
    val savedSearchStringLiveData = MutableLiveData("")


    val savedArticles = repository.getArticle()
    val newsArticles = MutableLiveData<NewsArticles>()


    val isInSearchMode = MutableLiveData(true)

    val searchFailed = repository.searchFailed

    init {
        viewModelScope.launch {
            try {
                val result = repository.getHeadlines()
                newsArticles.postValue(result)
            } catch (e: Exception) {
                Log.d("TAG", "$e: ")
            }
            isInSearchMode.postValue(false)
        }


    }


    fun sort(query: String) {
        if (query == AppConstants.SORT_ITEMS[0]) {


            val articles = newsArticles.value?.articles?.sortedBy {
                it.publishedAt.substring(8, 10)
            }?.reversed()


            newsArticles.postValue(articles?.let { NewsArticles(articles = it) })

        } else {
            val articles = newsArticles.value?.articles?.sortedBy {
                it.title
            }

            newsArticles.postValue(articles?.let { NewsArticles(articles = it) })


        }
    }


    fun onQuery(query: String) {

        searchStringLiveData.postValue(query)

    }

    private fun resetSearchState() {
        newsArticles.value = NewsArticles()
    }

    fun searchNews(query: String) {
        isInSearchMode.postValue(true)
        searchFailed.postValue(false)

        resetSearchState()
        viewModelScope.launch {
            try {

                val result = repository.searchArticle(query = query)

                if (result.totalResults == 0) {
                    searchFailed.postValue(true)
                } else
                    searchFailed.postValue(false)

                newsArticles.postValue(result)
            } catch (e: Exception) {
                Log.d("TAG", "searchNews: $e")
            }
            isInSearchMode.postValue(false)
        }
    }


    fun insertArticle(articleLocal: ArticleLocal) = viewModelScope.launch {
        repository.insertArticle(articleLocal)
    }

    fun deleteArticle(articleLocal: String) = viewModelScope.launch {
        repository.deleteArticle(articleLocal)
    }

}