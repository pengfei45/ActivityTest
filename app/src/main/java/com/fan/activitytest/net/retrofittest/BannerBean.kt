package com.fan.activitytest.net.retrofittest

import com.google.gson.annotations.SerializedName

data class BannerBean(
    @SerializedName("data")
    val data: List<BannerData>,
    val status: Int
)

data class BannerData(
    val image: String,
    val title: String,
    val url: String
)