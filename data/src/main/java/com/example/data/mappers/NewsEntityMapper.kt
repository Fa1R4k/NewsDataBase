package com.example.data.mappers

import com.example.data.database.NewsEntity
import com.example.data.models.NewsResponse
import javax.inject.Inject

class NewsEntityMapper @Inject constructor() {
    operator fun invoke(response: NewsResponse): NewsEntity =
        NewsEntity(
            title = response.title.orEmpty(),
            imageLink = response.urlToImage.orEmpty(),
            url = response.url.orEmpty(),
            author = response.author.orEmpty()
        )
}
