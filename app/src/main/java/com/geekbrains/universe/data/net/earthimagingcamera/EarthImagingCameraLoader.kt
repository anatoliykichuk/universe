package com.geekbrains.universe.data.net.earthimagingcamera

import com.geekbrains.universe.data.net.RetrofitClient
import com.geekbrains.universe.domain.EarthImagingCamera

const val BASE_URL = "https://epic.gsfc.nasa.gov"

class EarthImagingCameraLoader {

    fun load(date: String): EarthImagingCamera? {
        try {
            return loadSafety(date)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

    private fun loadSafety(date: String): EarthImagingCamera? {
        var earthImagingCamera: EarthImagingCamera? = null

        RetrofitClient.getClient(BASE_URL)
            .create(EarthImagingCameraApi::class.java)
            .getData(date)
            .execute().let {
                if (it.isSuccessful) {
                    val earthImagingCameraDto = it.body()!!
                    earthImagingCamera = EarthImagingCameraConverter.convertFromDto(
                        earthImagingCameraDto
                    )
                }
            }
        return earthImagingCamera
    }
}