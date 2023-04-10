package com.example.newsdatabase

import com.example.data.database.NewsEntity
import com.example.data.mappers.NewsEntityMapper
import com.example.data.models.NewsResponse
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NewsEntityMapperTest {
    @Test
    fun `test NewsEntityMapper NewsResponse to NewsEntity`() {
        val newsResponse = NewsResponse("News Title", "News Author", "News Url", "UrlImage")
        val newsEntity = NewsEntity("News Title", "News Author", "News Url", "UrlImage")

        assertEquals(newsEntity, NewsEntityMapper()(newsResponse))
    }

    @Test
    fun `test NewsEntityMapper emptyNewsResponse to emptyNewsEntity`() {
        val emptyNewsResponse = NewsResponse()
        val emptyNewsEntity = NewsEntity("", "", "", "")

        assertEquals(emptyNewsEntity, NewsEntityMapper()(emptyNewsResponse))
    }
}