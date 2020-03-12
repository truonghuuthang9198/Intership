package com.example.weatherappdemo.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappdemo.Model.AreaCountry
import com.example.weatherappdemo.Model.SearchHistory
import com.example.weatherappdemo.Repository.Repository
import com.example.weatherappdemo.Repository.SearchRepository
import com.example.weatherappdemo.RoomDatabase.SearchRoomDB
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import java.io.IOException
import kotlin.system.measureTimeMillis

class CountryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository

    init {
        repository = Repository()
    }

    suspend fun insert(s: String):ArrayList<AreaCountry>{
        val s: String = repository.fetchDoc(s)
        val arrayList = ArrayList<AreaCountry>()
        try {
            val json = JSONObject(s)
            val jsonObjectSearch = json.getJSONObject("search_api")
            val jsonArrayResult = jsonObjectSearch.getJSONArray("result")
            for (i in 0..jsonArrayResult.length() - 1) {
                val jsonObject = jsonArrayResult.getJSONObject(i)
                val jsonArrayArename = jsonObject.getJSONArray("areaName")
                val jsonObject1 = jsonArrayArename.getJSONObject(0)
                var areaName: String = jsonObject1.getString("value")
                val jsonArrayCountry = jsonObject.getJSONArray("country")
                val jsonObject2 = jsonArrayCountry.getJSONObject(0)
                var country:String = jsonObject2.getString("value")
                val jsonArrayRegion = jsonObject.getJSONArray("region")
                val jsonObject3 = jsonArrayRegion.getJSONObject(0)
                var region:String = jsonObject3.getString("value")

                if (region == "") {
                    var item1 = areaName + ", " + country
                    arrayList.add(AreaCountry(item1))
                }
                else
                {
                    var item2 = areaName + ", " + country + ", " + region
                    arrayList.add(AreaCountry(item2))
                }
            }
        }
        catch (e: IOException)
        {
            e.printStackTrace()
        }
        return arrayList
    }
    fun main(s: String) = runBlocking {
        val time = measureTimeMillis {
            val arrayList: Deferred<ArrayList<AreaCountry>> = async {
                insert(s)
            }
            return@runBlocking arrayList.await()
        }
    }
}