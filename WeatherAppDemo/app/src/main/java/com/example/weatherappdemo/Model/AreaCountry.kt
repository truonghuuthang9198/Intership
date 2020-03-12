package com.example.weatherappdemo.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AreaCountry(
    val areaName: String
): Parcelable