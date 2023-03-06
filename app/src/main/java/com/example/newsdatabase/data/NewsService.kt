package com.example.newsdatabase.data

import com.example.newsdatabase.data.models.NewsListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {
    @Headers("x-api-key: 655d3cf71e9d40d9aa4e47a35cf73343")
    @GET("everything")
    fun getNews(@Query("domains") domain: String): Call<NewsListResponse>
}