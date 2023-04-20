package com.geekbrains.universe.domain

import com.geekbrains.universe.data.net.pictureoftheday.MediaType
import java.time.LocalDate

class PictureOfTheDay(
    val date: LocalDate,
    val title: String,
    val explanation: String,
    val mediaType: MediaType,
    val url: String,
    val hdurl: String
) {

    fun getUrlFilled(): String {
        return if (url.isEmpty()) {
            hdurl
        } else {
            url
        }
    }
}