package com.arrkariz.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailGameResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("released")
    val released: String,

    @field:SerializedName("background_image")
    val background_image: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("description")
    val description: String,
)
