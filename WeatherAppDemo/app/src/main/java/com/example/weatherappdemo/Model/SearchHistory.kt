package com.example.weatherappdemo.Model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "search_table")
data class SearchHistory(
    @PrimaryKey
    @ColumnInfo(name = "querySearch")
    val querySearch: String
):Parcelable