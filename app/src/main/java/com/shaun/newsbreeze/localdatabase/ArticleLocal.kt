package com.shaun.newsbreeze.localdatabase

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.shaun.newsbreeze.models.Source


@Entity(tableName = "article", indices = [Index(value = ["title"], unique = true)])
data class ArticleLocal(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null

)
