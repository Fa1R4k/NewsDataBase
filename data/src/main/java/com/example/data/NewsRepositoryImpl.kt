package com.example.data

import com.example.data.mappers.NewsDataMapper
import com.example.data.mappers.NewsEntityMapper
import com.example.data.source.DataBaseSource
import com.example.domain.NewsData
import com.example.domain.NewsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val newsDataMapper: NewsDataMapper,
    private val newsEntityMapper: NewsEntityMapper,
    private val dataBaseSource: DataBaseSource,
) : NewsRepository {

    override fun getNews(): Completable =
        newsService.getNews("any").flatMapCompletable { single ->
            val mappedNews = single.articles?.map { newsEntityMapper(it) }.orEmpty()
            dataBaseSource.insertALl(mappedNews)
            Completable.complete()
        }


    override fun getNewsFromDataBase(): Observable<List<NewsData>> =
        dataBaseSource.getAll().map { observable -> observable.map { newsDataMapper(it) } }


    override fun search(query: String): Observable<List<NewsData>> {
        return newsService.getNews(query)
            .map { listResponse -> listResponse.articles?.map { newsDataMapper(it) } ?: emptyList() }.toObservable()
    }
}