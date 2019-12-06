package com.android.jsoup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(var context: Context, var titleList: ArrayList<String>, var date: ArrayList<String>) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_title.text = titleList[position]
        holder.tv_date.text = date[position]
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tv_title = itemView.findViewById<AppCompatTextView>(R.id.tv_title)
        val tv_date = itemView.findViewById<AppCompatTextView>(R.id.tv_date)
    }
}