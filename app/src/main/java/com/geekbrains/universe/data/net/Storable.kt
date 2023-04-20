package com.geekbrains.universe.data.net

import com.geekbrains.universe.domain.EarthImagingCamera
import com.geekbrains.universe.domain.PictureOfTheDay

interface Storable {
    fun getPictureOfTheDayFromNet(date: String): PictureOfTheDay?

    fun getEarthImagingCameraFromNet(date: String): EarthImagingCamera?
}