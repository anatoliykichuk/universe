package com.geekbrains.universe.domain

import com.geekbrains.universe.data.net.MediaType
import java.util.*

class PictureOfTheDay(
    private val date: Date,
    private val title: String,
    private val explanation: String,
    private val mediaType: MediaType,
    private val url: String,
    private val hdurl: String
) {
    fun getUrl(): String {
        return if (url.isEmpty()) {
            hdurl
        } else {
            url
        }
    }
}