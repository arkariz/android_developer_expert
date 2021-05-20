package com.arrkariz.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailGameResponse(
    @field:SerializedName("description")
    val description: String,
)
