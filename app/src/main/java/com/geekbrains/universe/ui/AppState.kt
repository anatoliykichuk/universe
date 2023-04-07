package com.geekbrains.universe.ui

import com.geekbrains.universe.domain.PictureOfTheDay

sealed class AppState {
    data class Success(val pictureOfTheDay: PictureOfTheDay?) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
