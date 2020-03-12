package com.example.weatherappdemo.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappdemo.Main2Activity
import com.example.weatherappdemo.Model.AreaCountry
import com.example.weatherappdemo.Model.SearchHistory
import com.example.weatherappdemo.R
import kotlinx.android.synthetic.main.coutry_item.view.*

class SearchHistoryAdapter internal constructor(
) : RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryAdapterViewHolder>() {
    private var searchHistory = emptyList<SearchHistory>()
    inner class SearchHistoryAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView!!.findViewById<TextView>(R.id.tv_countryname)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryAdapterViewHolder{
        val Context = parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.coutry_item,parent,false)

        val SearchHistoryAdapterViewHolder = SearchHistoryAdapterViewHolder(itemView)
        if(SearchHistoryAdapterViewHolder.itemView.carditem!=null)
        {
                SearchHistoryAdapterViewHolder.id.setOnClickListener {
                var Model = searchHistory.get(SearchHistoryAdapterViewHolder.adapterPosition)
                val Intent = Intent(Context, Main2Activity::class.java)
                Intent.putExtra("history", Model)
                Context.startActivity(Intent)
                }
        }
        return SearchHistoryAdapterViewHolder
    }

    override fun onBindViewHolder(holder: SearchHistoryAdapterViewHolder, position: Int) {
        val current = searchHistory[position]
        holder.id.text = current.querySearch
    }
    override fun getItemCount() = searchHistory.count()

    internal fun setKeySearch(searchHistory: List<SearchHistory>)
    {
        this.searchHistory =searchHistory
    }
}
