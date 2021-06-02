package com.arrkariz.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailGame(
    val id: Int,
    val name: String,
    var desc: String,
    val released: String,
    val background_image: String,
    val rating: Double,
    val isFavorite: Boolean
) : Parcelable
