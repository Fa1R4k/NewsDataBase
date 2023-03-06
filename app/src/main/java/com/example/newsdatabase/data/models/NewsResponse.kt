package com.example.newsapp.data.models

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("title") val title: String? = null,
    @SerializedName("urlToImage") val imageLink: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("author") val author: String? = null,
)