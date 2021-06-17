package com.shaun.newsbreeze.localdatabase

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(articleLocal: ArticleLocal)

    @Delete
    suspend fun deleteArticle(articleLocal: ArticleLocal)

    @Query("SELECT * FROM article")
    fun observeArticles(): LiveData<List<ArticleLocal>>

}