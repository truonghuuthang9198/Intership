package com.example.weatherappdemo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ColorSpace

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappdemo.Model.AreaCountry
import com.example.weatherappdemo.Model.SearchHistory
import com.example.weatherappdemo.ViewModel.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL

class Main2Activity() : AppCompatActivity(), Parcelable {

    lateinit var temp_C : TextView
    lateinit var humidity: TextView
    lateinit var uvindex: TextView
    lateinit var feellikeC: TextView
    lateinit var feellikeF: TextView
    lateinit var windspeed: TextView
    lateinit var pressure: TextView
    //lateinit var hudimy : TextView
    lateinit var weatherDesc : TextView
    private lateinit var searchViewModel: SearchViewModel

    lateinit var modelAreaCountry: AreaCountry
    lateinit var modelSearchHistory: SearchHistory

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherDesc = findViewById(R.id.weatherDesc)
        temp_C = findViewById(R.id.tempC)
        humidity = findViewById(R.id.humidity)
        uvindex = findViewById(R.id.uvindex)
        feellikeC = findViewById(R.id.feellikeC)
        feellikeF = findViewById(R.id.feellikeF)
        windspeed = findViewById(R.id.windspeed)
        pressure = findViewById(R.id.pressure)

        val intent = getIntent()
        try {
            // get info weather của list kết quả search
            modelAreaCountry = intent.getParcelableExtra<AreaCountry>("weather")
            searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
            var query: String = modelAreaCountry.areaName
            cityname.setText(query)
            if (searchViewModel.countKeySearch() < 6) {
                if (checkKeySearchInList()==true) {
                    searchViewModel.deleteHistorySearch(SearchHistory(query))
                    searchViewModel.insertHistorySearch(SearchHistory(query))
                }
                else {
                    searchViewModel.insertHistorySearch(SearchHistory(query))
                }
            }
            else
            {
                if(checkKeySearchInList()==true)
                {
                    searchViewModel.deleteHistorySearch(SearchHistory(query))
                    searchViewModel.insertHistorySearch(SearchHistory(query))
                }
                else {
                    searchViewModel.insertHistorySearch(SearchHistory(query))
                    var model = searchViewModel.getFirstKeySearch()
                    searchViewModel.deleteHistorySearch(model)
                }
            }
            ReadJson().execute("http://api.worldweatheronline.com/premium/v1/weather.ashx?format=json&key=6fe0e24c40354453beb152141202702&query="+query+"")
        }catch (e: Exception)
        {
            // get info weather của list search history
            modelSearchHistory = intent.getParcelableExtra<SearchHistory>("history")
            searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
            var query: String = modelSearchHistory.querySearch
            cityname.setText(query)
            searchViewModel.deleteHistorySearch(SearchHistory(query))
            searchViewModel.insertHistorySearch(SearchHistory(query))
            ReadJson().execute("http://api.worldweatheronline.com/premium/v1/weather.ashx?format=json&key=6fe0e24c40354453beb152141202702&query="+query+"")
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Main2Activity> {
        override fun createFromParcel(parcel: Parcel): Main2Activity {
            return Main2Activity(parcel)
        }

        override fun newArray(size: Int): Array<Main2Activity?> {
            return arrayOfNulls(size)
        }
    }

    private inner class ReadJson : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg strings: String): String {
            val content = StringBuilder()
            try {
                val url = URL(strings[0])
                val inputStreamReader = InputStreamReader(url.openConnection().getInputStream())
                val bufferedReader = BufferedReader(inputStreamReader)
                var line: String? = ""
                while ({ line = bufferedReader.readLine(); line }() != null) {
                    content.append(line)
                }
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return content.toString()
        }
        override fun onPostExecute(s: String) {

            super.onPostExecute(s)
            try {
                val json = JSONObject(s)
                val jsonObject = json.getJSONObject("data")
                val jsonArray = jsonObject.getJSONArray("current_condition")

                for (i in 0..jsonArray.length()-1) {
                    val JSONObject = jsonArray.getJSONObject(i)
                    val jsonArrayUrlImage = JSONObject.getJSONArray("weatherIconUrl")
                    val jsonObjectUrlImage = jsonArrayUrlImage.getJSONObject(0)
                    LoadImageWeather().execute(jsonObjectUrlImage.getString("value").toString())
                    temp_C.setText(JSONObject.getString("temp_C").toString()+"ºC")
                    windspeed.append(JSONObject.getString("windspeedKmph").toString()+" km/h")
                    humidity.append(JSONObject.getString("humidity").toString()+" %")
                    uvindex.append(JSONObject.getString("uvIndex").toString())
                    pressure.append(JSONObject.getString("pressure").toString()+" hPa")
                    feellikeC.append(JSONObject.getString("FeelsLikeC")+"º")
                    feellikeF.append(JSONObject.getString("FeelsLikeF")+"º")
                    val JSONArrayweatherDes = JSONObject.getJSONArray("weatherDesc")

                    for (j in 0.. JSONArrayweatherDes.length()-1)
                    {
                        val JSONObjectDes = JSONArrayweatherDes.getJSONObject(j)
                        weatherDesc.setText(JSONObjectDes.getString("value").toString())
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    private inner class LoadImageWeather: AsyncTask<String,Void,Bitmap>(){
        override fun doInBackground(vararg params: String?): Bitmap {
            val url = URL(params[0])
            val inputStream = url.openConnection().getInputStream()
            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)

            return bitmap
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            imageState.setImageBitmap(result)
        }
    }
    fun checkKeySearchInList():Boolean
    {
        val intent = getIntent()
        modelAreaCountry = intent.getParcelableExtra<AreaCountry>("weather")
        var query: String = modelAreaCountry.areaName
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val list = searchViewModel.getListSearchHistory()
        for (i in 0..list.size-1)
        {
            if(list.get(i).querySearch.compareTo(query) == 0)
            {
                return true
            }
        }
        return false
    }
}
