package com.example.data.source

import com.example.data.database.NewsDao
import com.example.data.database.NewsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataBaseSource @Inject constructor(
    private val newsDao: NewsDao,
) {
    fun getAll(): Flow<List<NewsEntity>> = newsDao.getAll()

    fun getAllWithoutFlow(): List<NewsEntity> = newsDao.getAllWithoutFlow()


    fun insertALl(users: List<NewsEntity>) = newsDao.insertALl(users)

    fun delete(users: List<NewsEntity>) = newsDao.delete(users)

}