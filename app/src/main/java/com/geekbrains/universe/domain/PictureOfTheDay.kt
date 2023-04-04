package com.geekbrains.universe.domain

import com.geekbrains.universe.data.net.MediaType
import java.util.*

class PictureOfTheDay(
    val date: Date,
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