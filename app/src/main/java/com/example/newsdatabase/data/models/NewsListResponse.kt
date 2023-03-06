package com.example.newsdatabase.data.models

import com.example.newsapp.data.models.NewsResponse
import com.google.gson.annotations.SerializedName

data class NewsListResponse(
    @SerializedName("articles") val articles: List<NewsResponse>? = null,
)