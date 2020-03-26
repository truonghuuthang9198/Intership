package com.example.demotuan2chuan

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.selects.select

@Dao
interface UserDao
{
//    @Insert()
//    fun insert(userClass: UserClass)
//
//    @Query("select * from tab_user ORDER BY username ASC")
//    fun getUserList():LiveData<List<UserClass>>
//
//    @Query("DELETE FROM tab_user")
//    fun deleteAll()
        @Insert()
        fun insert(user:UserClass)
        @Query("SELECT * from tab_user ORDER BY username ASC")
        fun getAllUsers():LiveData<List<UserClass>>
        @Query("DELETE FROM tab_user")
        fun deleteAll()
}