package com.example.domain

interface NewsRepository {

    fun getNews(): io.reactivex.Completable

    fun getNewsFromDataBase(): io.reactivex.Observable<List<NewsData>>

    fun search(query : String): io.reactivex.Observable<List<NewsData>>
}