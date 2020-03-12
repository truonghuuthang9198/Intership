package com.example.weatherappdemo.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherappdemo.Model.SearchHistory
import com.example.weatherappdemo.Repository.SearchRepository
import com.example.weatherappdemo.RoomDatabase.SearchRoomDB
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {


    private val searchRepository: SearchRepository
    val allkeySearch: LiveData<List<SearchHistory>>

    init {
        val searchDao = SearchRoomDB.getDatabase(application, viewModelScope).searchDao()
        searchRepository= SearchRepository(searchDao)
        allkeySearch = searchRepository.allKeySearch
    }

    fun insertHistorySearch(searchHistory: SearchHistory) = viewModelScope.launch {
        searchRepository.insertKey(searchHistory)
    }

    fun deleteHistorySearch(searchHistory: SearchHistory) = viewModelScope.launch {
        searchRepository.deleteKey(searchHistory)
    }
    fun countKeySearch(): Int{
        return searchRepository.countKeySearchHistory()
    }
    fun getFirstKeySearch():SearchHistory
    {
        return searchRepository.getFirstKeySearchHistory()
    }
    fun getListSearchHistory(): List<SearchHistory>
    {
        return searchRepository.getListSearchHistory()
    }
}