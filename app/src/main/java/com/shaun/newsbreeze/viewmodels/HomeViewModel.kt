package com.shaun.newsbreeze.viewmodels

import androidx.lifecycle.ViewModel
import com.shaun.newsbreeze.repository.HomeScreenRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homescreenrepositoryImpl: HomeScreenRepositoryImpl
) : ViewModel() {


    init {
        homescreenrepositoryImpl.getHeadlines()
    }
}