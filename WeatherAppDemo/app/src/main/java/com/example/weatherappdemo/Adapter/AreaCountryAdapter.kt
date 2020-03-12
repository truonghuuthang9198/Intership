package com.example.weatherappdemo.Adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappdemo.Main2Activity
import com.example.weatherappdemo.Model.AreaCountry
import com.example.weatherappdemo.R
import com.example.weatherappdemo.ViewModel.CountryViewModel
import kotlinx.android.synthetic.main.coutry_item.view.*


class AreaCountryAdapter internal constructor(val AreaCountry: ArrayList<AreaCountry>
) : RecyclerView.Adapter<AreaCountryAdapter.AreaCountryAdapterViewHolder>() {
    inner class AreaCountryAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView!!.findViewById<TextView>(R.id.tv_countryname)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaCountryAdapterViewHolder{
        val Context = parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.coutry_item,parent,false)

        val AreaCountryAdapterViewHolder = AreaCountryAdapterViewHolder(itemView)
        if(AreaCountryAdapterViewHolder.itemView.carditem!=null)
        {
            AreaCountryAdapterViewHolder.id.setOnClickListener {
                var Model = AreaCountry.get(AreaCountryAdapterViewHolder.adapterPosition)
                val Intent = Intent(Context, Main2Activity::class.java)
                Intent.putExtra("weather", Model)
                Context.startActivity(Intent)
            }
        }
        return AreaCountryAdapterViewHolder
    }

    override fun onBindViewHolder(holder: AreaCountryAdapterViewHolder, position: Int) {
        val current = AreaCountry[position]
        holder.id.text = current.areaName
    }
    override fun getItemCount() = AreaCountry.count()
}





