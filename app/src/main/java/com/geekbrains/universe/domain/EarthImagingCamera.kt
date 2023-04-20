package com.geekbrains.universe.domain

import com.google.gson.annotations.SerializedName

data class EarthImagingCamera(
    val identifier: String? = null,
    val caption: String? = null,
    val image: String? = null,
    val version: String? = null,
    val date: String? = null
)