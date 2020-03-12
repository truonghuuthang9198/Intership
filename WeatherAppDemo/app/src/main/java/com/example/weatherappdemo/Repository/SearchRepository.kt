package com.example.weatherappdemo.Repository

import androidx.lifecycle.LiveData
import com.example.weatherappdemo.Model.SearchHistory
import com.example.weatherappdemo.RoomDatabase.SearchDao

class SearchRepository(private val searchDao: SearchDao){
    val allKeySearch: LiveData<List<SearchHistory>> = searchDao.getKeySearch()

    fun insertKey(searchHistory: SearchHistory)
    {
        searchDao.insertKeySearch(searchHistory)
    }
    fun deleteKey(searchHistory: SearchHistory)
    {
        searchDao.deleteKeySearch(searchHistory)
    }
    fun countKeySearchHistory():Int
    {
        return searchDao.countKeySearch()
    }
    fun getFirstKeySearchHistory():SearchHistory {
        return searchDao.getFirstKeySearch()
    }
    fun getListSearchHistory():List<SearchHistory>
    {
        return searchDao.getListHistory()
    }
}