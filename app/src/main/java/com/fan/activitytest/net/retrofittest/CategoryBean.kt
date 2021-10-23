package com.fan.activitytest.net.retrofittest

import com.google.gson.annotations.SerializedName

data class CategoryBean(
    @SerializedName("data")
    val data: List<CategoryData>,
    val page: Int,
    val page_count: Int,
    val status: Int,
    val total_counts: Int
)

data class CategoryData(
    val _id: String,
    val author: String,
    val category: String,
    val createdAt: String,
    val desc: String,
    val images: List<String>,
    val likeCounts: Int,
    val publishedAt: String,
    val stars: Int,
    val title: String,
    val type: String,
    val url: String,
    val views: Int
)