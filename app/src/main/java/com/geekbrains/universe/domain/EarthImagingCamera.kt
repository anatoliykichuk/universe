package com.geekbrains.universe.domain

import java.time.LocalDate

const val BASE_URL = "https://epic.gsfc.nasa.gov/archive/natural"

data class EarthImagingCamera(
    val identifier: String? = null,
    val caption: String? = null,
    val image: String? = null,
    val version: String? = null,
    val date: String? = null
) {
    fun getImageUrl(): String? {
        if (image == null || date == null) {
            return null
        }

        val imageName = image!!.replace("RGB", "1b")
        val imageDate = LocalDate.parse(date!!)

        return "$BASE_URL/${imageDate.year}/${imageDate.month}/${imageDate.dayOfMonth}/$imageName.png"
    }
}