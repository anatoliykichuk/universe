package com.geekbrains.universe.data.net.earthimagingcamera

import com.geekbrains.universe.domain.EarthImagingCamera

object EarthImagingCameraConverter {
    fun convertFromDto(earthImagingCameraDto: EarthImagingCameraDto): EarthImagingCamera {
        return EarthImagingCamera(
            identifier = earthImagingCameraDto.identifier,
            caption = earthImagingCameraDto.caption,
            image = earthImagingCameraDto.image,
            version = earthImagingCameraDto.version,
            date = earthImagingCameraDto.date
        )
    }
}