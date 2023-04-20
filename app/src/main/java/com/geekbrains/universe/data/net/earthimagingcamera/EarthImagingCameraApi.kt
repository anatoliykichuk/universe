package com.geekbrains.universe.data.net.earthimagingcamera

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val END_POINT = "api/enhanced/date"

interface EarthImagingCameraApi {
    @GET(END_POINT)
    fun getData(
        @Query("date") date: String
    ): Call<EarthImagingCameraDto>
}