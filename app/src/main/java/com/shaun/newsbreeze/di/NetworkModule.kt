package com.shaun.newsbreeze.di

import com.google.gson.GsonBuilder
import com.shaun.newsbreeze.network.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("api")
    fun getAPIKey() = "c5505b6406384fe2b1060c7dd66e957c"

    @Provides
    @Singleton
    fun provideSpotifyService(): NewsApiService =
        Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
            .create(NewsApiService::class.java)
}