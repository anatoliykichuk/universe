package com.geekbrains.universe.data.net

import com.geekbrains.universe.domain.PictureOfTheDay

class Repository: Storable {
    override fun getPictureFromNet(): PictureOfTheDay? {
        return PictureOfTheDayLoader().load()
    }
}