package com.geekbrains.universe.data.net

import com.geekbrains.universe.BuildConfig
import com.geekbrains.universe.domain.PictureOfTheDay

class PictureOfTheDayLoader {

    fun load(date: String): PictureOfTheDay? {
        try {
            return loadSafety(date)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

    private fun loadSafety(date: String): PictureOfTheDay? {
        var pictureOfTheDay: PictureOfTheDay? = null

        RetrofitClient
            .getClient()
            .create(PictureOfTheDayApi::class.java)
            .getPicture(BuildConfig.NASA_API_KEY, date)
            .execute().let {
                if (it.isSuccessful) {
                    val pictureOfTheDayDto = it.body()!!
                    pictureOfTheDay = PictureOfTheDayConverter.convertFromDto(
                        pictureOfTheDayDto
                    )
                }
            }
        return pictureOfTheDay
    }
}