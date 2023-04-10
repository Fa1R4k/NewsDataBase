package com.example.data.mappers

import com.example.data.database.NewsEntity
import com.example.data.models.NewsResponse
import com.example.domain.NewsData
import javax.inject.Inject

class NewsDataMapper @Inject constructor() {

    operator fun invoke(response: NewsResponse): NewsData = with(response) {
        return NewsData(
            title = title.orEmpty(),
            imageLink = urlToImage.orEmpty(),
            url = url.orEmpty(),
            author = author.orEmpty()
        )
    }

    operator fun invoke(entity: NewsEntity): NewsData = with(entity) {
        return NewsData(
            title = title,
            imageLink = imageLink,
            url = url,
            author = author
        )
    }
}