package com.geekbrains.universe.data.net

import com.geekbrains.universe.domain.PictureOfTheDay

class Repository: Storable {
    override fun getPictureFromNet(date: String): PictureOfTheDay? {
        return PictureOfTheDayLoader().load(date)
    }
}