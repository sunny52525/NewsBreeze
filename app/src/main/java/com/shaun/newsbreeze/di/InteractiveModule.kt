package com.shaun.newsbreeze.di

import com.shaun.newsbreeze.network.NewsApiService
import com.shaun.newsbreeze.repository.HomeScreenRepository
import com.shaun.newsbreeze.repository.HomeScreenRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractiveModule {
    @Provides
    @Singleton

    fun provideRepository(apiKey: String, retrofit: NewsApiService):HomeScreenRepository {
        return HomeScreenRepositoryImpl(apiKey = apiKey, retrofit = retrofit)
    }
}