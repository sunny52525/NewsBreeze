package com.shaun.newsbreeze.di

import android.content.Context
import com.shaun.newsbreeze.NewsBreezeApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApplication(@ApplicationContext app: Context): NewsBreezeApplication {
        return app as NewsBreezeApplication
    }
}