package com.shaun.newsbreeze.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.shaun.newsbreeze.models.NewsArticles
import com.shaun.newsbreeze.repository.HomeScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeScreenRepository
) : ViewModel() {

    private val _searchStringLiveData = MutableLiveData<String>(null)


    val isInSearchMode = MutableLiveData(true)

    val searchFailed = repository.searchFailed

    var topHeadlines = Transformations.switchMap(_searchStringLiveData) {
        if (it.isNullOrEmpty())
            repository.getHeadlines()
        else
            repository.searchArticle(it)
    }

    val onHomeScreen = MutableLiveData<NewsArticles>()

    init {
        topHeadlines.observeForever {
            onHomeScreen.postValue(it)
        }

    }

    fun searchNews(query: String) {
        searchFailed.postValue(false)
        isInSearchMode.postValue(true)
        onHomeScreen.postValue(NewsArticles())
        _searchStringLiveData.postValue(query)
    }
}