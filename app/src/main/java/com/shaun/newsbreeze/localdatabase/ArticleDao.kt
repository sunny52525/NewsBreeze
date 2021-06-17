package com.shaun.newsbreeze.localdatabase

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(articleLocal: ArticleLocal)



    @Query("DELETE FROM article where title=:title")
    suspend fun deleteArticle(title:String)

    @Query("SELECT * FROM article")
    fun observeArticles(): LiveData<List<ArticleLocal>>

}