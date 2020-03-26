package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demotuan2chuan.MainActivity
import com.example.demotuan2chuan.R

class Adapter internal constructor(
    val context: MainActivity, val messages: ArrayList<Messages>
) : RecyclerView.Adapter<Adapter.MessageViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView!!.findViewById<TextView>(R.id.id)
        val from = itemView!!.findViewById<TextView>(R.id.from)
        val email = itemView!!.findViewById<TextView>(R.id.eamil)
        val subject = itemView!!.findViewById<TextView>(R.id.subject)
        val message = itemView!!.findViewById<TextView>(R.id.message)
        val date = itemView!!.findViewById<TextView>(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val itemView = inflater.inflate(R.layout.recycleview_item, parent, false)
        return MessageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val current = messages[position]
        holder.id.text = current.id
        holder.from.text=current.from
        holder.email.text=current.email
        holder.subject.text=current.subject
        holder.message.text=current.message
        holder.date.text=current.date
    }
    override fun getItemCount() = messages.count()
}