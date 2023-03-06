package com.example.newsdatabase.data.mappers

import com.example.newsapp.data.models.NewsResponse
import com.example.newsdatabase.data.database.NewsEntity
import com.example.newsapp.domain.NewsData
import javax.inject.Inject

class NewsDataMapper @Inject constructor() {

    operator fun invoke(response: NewsResponse): NewsData = with(response) {
        return NewsData(
            title = title.orEmpty(),
            imageLink = imageLink.orEmpty(),
            url = url.orEmpty(),
            author = author.orEmpty()
        )
    }
    operator fun invoke(entity: NewsEntity): NewsData = with(entity) {
        return NewsData(
            title = title.orEmpty(),
            imageLink = imageLink.orEmpty(),
            url = url.orEmpty(),
            author = author.orEmpty()
        )
    }
}