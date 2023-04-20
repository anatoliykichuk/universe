package com.geekbrains.universe.data.net

import com.geekbrains.universe.data.net.earthimagingcamera.EarthImagingCameraLoader
import com.geekbrains.universe.data.net.pictureoftheday.PictureOfTheDayLoader
import com.geekbrains.universe.domain.EarthImagingCamera
import com.geekbrains.universe.domain.PictureOfTheDay

class Repository: Storable {
    override fun getPictureOfTheDayFromNet(date: String): PictureOfTheDay? {
        return PictureOfTheDayLoader().load(date)
    }

    override fun getEarthImagingCameraFromNet(date: String): EarthImagingCamera? {
        return EarthImagingCameraLoader().load(date)
    }
}