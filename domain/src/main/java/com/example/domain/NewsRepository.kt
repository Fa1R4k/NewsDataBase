package com.example.domain

import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews()

    suspend fun getNewsFromDataBase(): Flow<List<NewsData>>

    suspend fun search(query : String): List<NewsData>
}