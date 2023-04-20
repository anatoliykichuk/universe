package com.geekbrains.universe.data.net.pictureoftheday

import com.geekbrains.universe.BuildConfig
import com.geekbrains.universe.data.net.RetrofitClient
import com.geekbrains.universe.domain.PictureOfTheDay

const val BASE_URL = "https://api.nasa.gov"

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

        RetrofitClient.getClient(BASE_URL)
            .create(PictureOfTheDayApi::class.java)
            .getData(BuildConfig.NASA_API_KEY, date)
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