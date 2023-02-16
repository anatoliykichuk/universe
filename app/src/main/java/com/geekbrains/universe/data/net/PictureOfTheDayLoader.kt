package com.geekbrains.universe.data.net

import com.geekbrains.universe.BuildConfig
import com.geekbrains.universe.domain.PictureOfTheDay

class PictureOfTheDayLoader {

    fun load(): PictureOfTheDay? {
        try {
            return loadSafety()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

    private fun loadSafety(): PictureOfTheDay? {
        val pictureOfTheDayApi = RetrofitClient
            .getClient()
            .create(PictureOfTheDayApi::class.java)
            .getPicture(BuildConfig.NASA_API_KEY)
            .execute().let {
                if (it.isSuccessful) {
                    val pictureOfTheDayDto = it.body()

                    pictureOfTheDayDto?.let { pictureOfTheDayDto ->
                        PictureOfTheDayConverter.convertFromDto(
                            pictureOfTheDayDto
                        )
                    }
                }
            }
        return null
    }
}