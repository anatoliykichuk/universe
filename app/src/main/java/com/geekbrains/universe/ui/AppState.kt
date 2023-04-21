package com.geekbrains.universe.ui

import com.geekbrains.universe.domain.EarthImagingCamera
import com.geekbrains.universe.domain.PictureOfTheDay

sealed class AppState {
    data class SuccessPictureOfTheDay(val pictureOfTheDay: PictureOfTheDay?) : AppState()
    data class SuccessEarthImagingCamera(val earthImagingCamera: EarthImagingCamera?) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
