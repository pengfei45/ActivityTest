package com.fan.fanweather.logic.network

import com.fan.fanweather.App
import com.fan.fanweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${App.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String):Call<PlaceResponse>
}