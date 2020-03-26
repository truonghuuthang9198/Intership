package com.example.demotuan2chuan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "tab_user")
data class UserClass(
    @PrimaryKey
    @ColumnInfo(name="username")
    val username: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "gender")
    val gender: Int

)
