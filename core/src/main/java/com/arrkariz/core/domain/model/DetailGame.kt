package com.arrkariz.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailGame(
    val name: String,
    val description: String,
    val released: String,
    val background_image: String,
    val rating: Int
) : Parcelable
