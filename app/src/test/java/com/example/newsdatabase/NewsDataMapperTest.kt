package com.example.newsdatabase

import com.example.data.database.NewsEntity
import com.example.data.mappers.NewsDataMapper
import com.example.data.models.NewsResponse
import com.example.domain.NewsData
import org.junit.Assert
import org.junit.Test

class NewsDataMapperTest {

    @Test
    fun `test NewsDataMapper NewsResponse to NewsData`() {
        val newsResponse = NewsResponse("News Title", "News Author", "News Url", "UrlImage")
        val newsData = NewsData("News Title", "News Author", "News Url", "UrlImage")

        Assert.assertEquals(newsData, NewsDataMapper()(newsResponse))
    }

    @Test
    fun `test NewsEntityMapper emptyNewsResponse to emptyNewsData`() {
        val emptyNewsResponse = NewsResponse()
        val newsData = NewsData("", "", "", "")

        Assert.assertEquals(newsData, NewsDataMapper()(emptyNewsResponse))
    }

    @Test
    fun `test NewsDataMapper NewsEntity to NewsData`() {
        val newsEntity = NewsEntity("News Title", "News Author", "News Url", "UrlImage")
        val newsData = NewsData("News Title", "News Author", "News Url", "UrlImage")

        Assert.assertEquals(newsData, NewsDataMapper()(newsEntity))
    }
}