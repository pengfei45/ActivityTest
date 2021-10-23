package com.fan.activitytest.net.retrofittest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ShowService {

    @GET("v2/banners")
    fun getGankBannerData(): Call<BannerBean>

    @GET("v2/data/category/{category}/type/{type}/page/{page}/count/{count}")
    fun getGankCategoryData(
        @Path("category") category: String,
        @Path("type") type: String,
        @Path("page") page: Int,
        @Path("count") count: Int
    ): Call<CategoryBean>

}