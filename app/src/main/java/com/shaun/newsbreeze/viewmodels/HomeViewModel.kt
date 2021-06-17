package com.shaun.newsbreeze.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaun.newsbreeze.models.NewsArticles
import com.shaun.newsbreeze.repository.HomeScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeScreenRepository
) : ViewModel() {

    private val _searchStringLiveData = MutableLiveData<String>(null)


    val newsArticles = MutableLiveData<NewsArticles>()

    val searchQuery = MutableLiveData("")

    val isInSearchMode = MutableLiveData(true)

    val searchFailed = repository.searchFailed

    init {

        viewModelScope.launch {
            val result = repository.getHeadlines()
            newsArticles.postValue(result)
            isInSearchMode.postValue(false)
        }
    }


    private fun resetSearchState() {
        newsArticles.value = NewsArticles()
    }

    fun searchNews(query: String) {
        isInSearchMode.postValue(true)
        searchFailed.postValue(false)

        resetSearchState()
        viewModelScope.launch {
            val result = repository.searchArticle(query = query)

            if (result.totalResults == 0) {
                searchFailed.postValue(true)
            } else
                searchFailed.postValue(false)

            newsArticles.postValue(result)
            isInSearchMode.postValue(false)
        }
    }
}