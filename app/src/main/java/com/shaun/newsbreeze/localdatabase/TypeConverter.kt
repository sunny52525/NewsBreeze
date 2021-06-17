package com.shaun.newsbreeze.localdatabase

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shaun.newsbreeze.models.Source

class TypeConverter {
    private val gSon = Gson()

    @TypeConverter
    fun fromSource(source: Source): String {
        return gSon.toJson(source)
    }

    @TypeConverter
    fun tvSource(name: String): Source {
        val listType = object : TypeToken<Source>() {}.type
        return gSon.fromJson(name, listType)
    }
}