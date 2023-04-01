package com.example.data

import com.example.data.models.NewsListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {
    @Headers("x-api-key: 655d3cf71e9d40d9aa4e47a35cf73343")
    @GET("everything")
    fun getNews(@Query("q") q: String): Single<NewsListResponse>
}