package com.arrkariz.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailGameResponse(
    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("released")
    val released: String,

    @field:SerializedName("background_image")
    val background_image: String,

    @field:SerializedName("rating")
    val rating: Int
)
