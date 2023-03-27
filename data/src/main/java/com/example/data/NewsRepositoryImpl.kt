package com.example.data

import com.example.data.mappers.NewsDataMapper
import com.example.data.mappers.NewsEntityMapper
import com.example.data.source.DataBaseSource
import com.example.domain.NewsData
import com.example.domain.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val newsDataMapper: NewsDataMapper,
    private val newsEntityMapper: NewsEntityMapper,
    private val dataBaseSource: DataBaseSource,
) : NewsRepository {

    override suspend fun getNews() {
        withContext(Dispatchers.IO) {
            dataBaseSource.delete(dataBaseSource.getAllWithoutFlow())
            val articles = newsService.getNews("Tesla").execute().body()
                ?: throw Exception()
            articles.articles?.let { newsEntityMapper(it) }?.let { dataBaseSource.insertALl(it) }
            articles.articles?.map { newsDataMapper(it) } ?: listOf()
        }
    }

    override suspend fun getNewsFromDataBase(): Flow<List<NewsData>> =
        withContext(Dispatchers.IO) {
            dataBaseSource.getAll().map { newsDataMapper.entityListToDataList(it) }
        }

    override suspend fun search(query: String): List<NewsData> = withContext(Dispatchers.IO) {
        val articles = newsService.getNews(query).execute().body() ?: throw Exception()
        articles.articles?.map { newsDataMapper(it) } ?: listOf()
    }
}