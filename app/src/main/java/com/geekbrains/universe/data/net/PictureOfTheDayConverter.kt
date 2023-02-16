package com.geekbrains.universe.data.net

import com.geekbrains.universe.domain.PictureOfTheDay
import java.util.*

const val VIDEO_MEDIA_TYPE = "video"

object PictureOfTheDayConverter {
    fun convertFromDto(pictureOfTheDayDto: PictureOfTheDayDto): PictureOfTheDay {
        return PictureOfTheDay(
            date = Date(pictureOfTheDayDto.date),
            title = pictureOfTheDayDto.title,
            explanation = pictureOfTheDayDto.explanation,
            mediaType = mediaType(pictureOfTheDayDto.mediaType),
            url = pictureOfTheDayDto.url,
            hdurl = pictureOfTheDayDto.hdurl
        )
    }

    private fun mediaType(mediaTypeRepresentation: String): MediaType {
        return if (mediaTypeRepresentation.lowercase().equals(VIDEO_MEDIA_TYPE)) {
            MediaType.VIDEO
        } else {
            MediaType.IMAGE
        }
    }
}