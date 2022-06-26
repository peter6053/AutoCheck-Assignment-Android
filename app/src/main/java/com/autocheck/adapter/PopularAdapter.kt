package com.autocheck.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.autocheck.R
import com.autocheck.model.popularModel.Make
import com.bumptech.glide.Glide


class PopularAdapter (private val popularList:ArrayList<Make>) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.popular_item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(popularList[position])
    }

    override fun getItemCount(): Int {
        return popularList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindHolder(make: Make) {
            val img = itemView.findViewById(R.id.popular_image) as ImageView
            val title = itemView.findViewById(R.id.popular_title) as TextView
            title.text = make.name
            if (make.imageUrl.endsWith(".svg"))
                Glide.with(itemView.context).load(R.drawable.ic_launcher_foreground).into(img)
            else
                Glide.with(itemView.context).load(make.imageUrl).into(img)
        }

    }

}