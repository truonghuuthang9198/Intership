package com.example.demotuan2chuan

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.Adapter
import com.example.myapplication.Messages
import com.google.android.material.tabs.TabLayout
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        TabLayOutManager()
        ReadJson().execute("https://api.androidhive.info/mail/inbox.json")
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
            val books = ArrayList<Messages>()
            try {
                val json = JSONObject(s)
                val jsonArray = json.getJSONArray("messages")
                for (i in 0..jsonArray.length()-1) {
                    val JSONObject = jsonArray.getJSONObject(i)
                    var id: String = JSONObject.getString("id")
                    var from: String = JSONObject.getString("from")
                    var email: String = JSONObject.getString("email")
                    var subject: String = JSONObject.getString("subject")
                    var message: String = JSONObject.getString("message")
                    var date: String = JSONObject.getString("date")
                    books.add(Messages(id,from,email,subject,message,date))
                }
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
                val adapter = Adapter(this@MainActivity,books)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.setLayoutManager(LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    fun TabLayOutManager()
    {
        var tabLayout: TabLayout? = null
        var viewPager: ViewPager? = null

        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager = findViewById<ViewPager>(R.id.viewPager)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Details"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("List"))
        tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.ic_user)
        tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.ic_list_black_24dp)
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = MyPageAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }
}
