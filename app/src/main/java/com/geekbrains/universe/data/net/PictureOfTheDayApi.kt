package com.geekbrains.universe.data.net

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val END_POINT = "planetary/apod"

interface PictureOfTheDayApi {
    @GET(END_POINT)
    fun getPicture(
        @Query("api_key") apiKey: String,
        @Query("date") date: String
    ): Call<PictureOfTheDayDto>
}