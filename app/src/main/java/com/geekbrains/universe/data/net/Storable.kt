package com.geekbrains.universe.data.net

import com.geekbrains.universe.domain.PictureOfTheDay

interface Storable {
    fun getPictureFromNet(): PictureOfTheDay?
}