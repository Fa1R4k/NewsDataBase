package com.example.domain

import io.reactivex.Completable
import io.reactivex.Observable

interface NewsRepository {
    fun getNews(): Completable
    fun getNewsFromDataBase(): Observable<List<NewsData>>
    fun search(query : String): Observable<List<NewsData>>
}