package com.shaun.newsbreeze.viewmodels

import android.util.Log
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

    val _searchStringLiveData = MutableLiveData("")


    val newsArticles = MutableLiveData<NewsArticles>()

    val searchQuery = MutableLiveData("")

    val isInSearchMode = MutableLiveData(true)

    val searchFailed = repository.searchFailed

    init {
        viewModelScope.launch {
            try {
                val result = repository.getHeadlines()
                newsArticles.postValue(result)
            }catch (e:Exception){
                Log.d("TAG", "$e: ")
            }
            isInSearchMode.postValue(false)
        }
    }


    fun onQuery(query: String) {
        _searchStringLiveData.postValue(query)

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
           }catch (e:Exception){
               Log.d("TAG", "searchNews: $e")
           }
            isInSearchMode.postValue(false)
        }
    }
}