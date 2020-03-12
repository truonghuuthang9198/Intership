package com.example.weatherappdemo

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView;
import androidx.core.view.get
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappdemo.Adapter.AreaCountryAdapter
import com.example.weatherappdemo.Adapter.SearchHistoryAdapter
import com.example.weatherappdemo.Model.AreaCountry
import com.example.weatherappdemo.Model.SearchHistory
import com.example.weatherappdemo.ViewModel.CountryViewModel
import com.example.weatherappdemo.ViewModel.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main1.*
import kotlinx.android.synthetic.main.coutry_item.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerView1: RecyclerView
    private lateinit var countryViewModel: CountryViewModel
    private lateinit var arrayListCountry: ArrayList<AreaCountry>
    private lateinit var arrayListSearch: ArrayList<SearchHistory>
    private var editSearchView: SearchView? = null
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        recyclerView = findViewById(R.id.recycleViewCountry)
        recyclerView1 = findViewById(R.id.recyclerViewSearchHistory)

        editSearchView = findViewById(R.id.searchView1)
        editSearchView!!.setOnQueryTextListener(this)

        setHistory()

        //ReadJson().execute("http://api.worldweatheronline.com/premium/v1/weather.ashx?key=8e2c02914bb04153a4834308202702&q=Canada&format=json&num_of_days=5&date=today&fx=yes&cc=yes&mca=no&fx24=no&includelocation=yes&show_comments=yes&tp=3&showlocaltime=yes&lang=vi")
    }


    fun SearchAreaCountry(query: String?)
    {
        recyclerView.layoutManager = LinearLayoutManager(this)
        countryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        arrayListCountry = countryViewModel.main("http://api.worldweatheronline.com/premium/v1/search.ashx?format=json&key=b9c3c03630e545dba9775353201902&query="+query+"") as ArrayList<AreaCountry>
        val adapter = AreaCountryAdapter(arrayListCountry)
        recyclerView.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        SearchAreaCountry(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText?.count() == 0)
        {
            setHistory()
        }
        else {
            try {
                if (newText?.isNotEmpty() == true && newText.count() > 2) {
                    SearchAreaCountry(newText)
                }
                return true
            } catch (e: Exception) {
            }
        }
        return true
    }
    fun setHistory()
    {
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter1 = SearchHistoryAdapter()
        recyclerView.adapter = adapter1
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchViewModel.allkeySearch.observe(this, Observer<List<SearchHistory>> {
            val lst: MutableList<SearchHistory> = mutableListOf()
            for (i in 0..it.size-1)
            {
                lst.add(0, it.get(i))
            }
            adapter1.setKeySearch(lst)
        })
    }

    override fun onRestart() {
        super.onRestart()
        setHistory()
    }

}

