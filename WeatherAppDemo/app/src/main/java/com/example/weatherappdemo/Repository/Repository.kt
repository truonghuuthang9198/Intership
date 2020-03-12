package com.example.weatherappdemo.Repository

import com.example.weatherappdemo.RoomDatabase.SearchDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.MalformedURLException
import java.net.URL

class Repository{
    suspend fun fetchDoc(s: String): String {
        val result = getUrl(s)
        return result
    }

    suspend fun getUrl(url: String) = withContext(Dispatchers.IO){
        val content = StringBuilder()
        try {
            val url = URL(url)
            val inputStreamReader = InputStreamReader(url.openConnection().getInputStream())
            val bufferedReader = BufferedReader(inputStreamReader)
            var line: String? = ""
            while ({line = bufferedReader.readLine();line}()!=null)
            {
                content.append(line)
            }
        }catch (e: MalformedURLException)
        {
            e.printStackTrace()
        }
        catch (e: IOException)
        {
            e.printStackTrace()
        }
        return@withContext content.toString()
    }
}