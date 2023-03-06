package com.example.newsdatabase.data

import com.example.newsdatabase.data.database.NewsDao
import com.example.newsdatabase.data.mappers.NewsDataMapper
import com.example.newsdatabase.data.mappers.NewsEntityMapper
import com.example.newsapp.domain.NewsData
import com.example.newsapp.domain.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val newsDataMapper: NewsDataMapper,
    private val newsEntityMapper: NewsEntityMapper,
    private val newsDao: NewsDao,
) : NewsRepository {

    override suspend fun getListNewsData(): List<NewsData> {
        return withContext(Dispatchers.IO) {
            val articles = newsService.getNews("bbc.com").execute().body()
                ?: throw Exception()
            articles.articles?.let { newsEntityMapper(it) }?.let { newsDao.insertALl(it) }
            articles.articles?.map { newsDataMapper(it) } ?: listOf()
        }
    }

    override suspend fun getListNewsDataFromDataBase(): List<NewsData> {
        return withContext(Dispatchers.IO) {
            newsDao.getAll().map { newsDataMapper(it) }.toList()
        }
    }
}