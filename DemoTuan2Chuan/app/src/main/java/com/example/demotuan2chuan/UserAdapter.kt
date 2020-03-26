package com.example.demotuan2chuan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class UserAdapter()
    :RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    //private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var user = emptyList<UserClass>() // Cached copy of words
    inner class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val username: TextView = itemView.findViewById(R.id.username)
        val password: TextView = itemView.findViewById(R.id.password)
        val email: TextView = itemView.findViewById(R.id.email)
        val address: TextView = itemView.findViewById(R.id.address)
        val gender: TextView = itemView.findViewById(R.id.gender)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
//        val itemView = inflater.inflate(R.layout.recycleview_useritem, parent, false)
//        return UserViewHolder(itemView)
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycleview_useritem,parent,false)
        return UserViewHolder(cellForRow)
    }

    override fun getItemCount() = user.size
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = user[position]
        holder.username.text = current.username
        holder.password.text = current.password
        holder.email.text = current.email
        holder.address.text = current.address
        holder.gender.text = current.gender.toString()
    }
    internal fun setUser(user: List<UserClass>) {
        this.user =user
        notifyDataSetChanged()
    }

}