package com.example.weatherappdemo.RoomDatabase

import android.graphics.ColorSpace
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weatherappdemo.Model.SearchHistory

@Dao
interface SearchDao{
    @Query("SELECT Count(*) from search_table")
    fun countKeySearch():Int

    @Query("SELECT * from search_table")
    fun getKeySearch(): LiveData<List<SearchHistory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertKeySearch(searchHistory: SearchHistory)

    @Delete
    fun deleteKeySearch(searchHistory: SearchHistory)

    @Query("Delete From search_table")
    fun deleteAll()

    @Query("SELECT * from search_table LIMIT 1")
    fun getFirstKeySearch():SearchHistory

    @Query("SELECT * from search_table")
    fun getListHistory():List<SearchHistory>
}