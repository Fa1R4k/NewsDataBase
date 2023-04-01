package com.example.data.source

import com.example.data.database.NewsDao
import com.example.data.database.NewsEntity
import io.reactivex.Observable
import javax.inject.Inject

class DataBaseSource @Inject constructor(
    private val newsDao: NewsDao,
) {
    fun getAll(): Observable<List<NewsEntity>> = newsDao.getAll()

    fun insertALl(users: List<NewsEntity>) = newsDao.insertALl(users)

    fun delete(users: List<NewsEntity>) = newsDao.delete(users)

}