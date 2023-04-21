package com.geekbrains.universe.data.net.earthimagingcamera

import com.google.gson.annotations.SerializedName

data class EarthImagingCameraDto(
    @SerializedName("identifier") var identifier: String? = null,
    @SerializedName("caption") var caption: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("version") var version: String? = null,
    @SerializedName("date") var date: String? = null
)