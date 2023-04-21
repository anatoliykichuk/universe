package com.geekbrains.universe.data.net.earthimagingcamera

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EarthImagingCameraApi {
    @GET("api/enhanced/{date}")
    fun getData(
        @Path("date") date: String
    ): Call<EarthImagingCameraDto>
}