package com.autocheck.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.autocheck.DetailActivity
import com.autocheck.R
import com.autocheck.model.CarListModel.Result
import com.bumptech.glide.Glide

class CarAdapter(private val carList:ArrayList<com.autocheck.model.CarListModel.Result>) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.car_item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(carList[position])
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img = itemView.findViewById<ImageView>(R.id.carImage)
        var title = itemView.findViewById<TextView>(R.id.title)
        var city = itemView.findViewById<TextView>(R.id.city)
        var price = itemView.findViewById<TextView>(R.id.price)
        fun bindHolder(result: Result) {
            title.text = result.title
            city.text = result.city
            price.text = result.marketplacePrice.toString()
            Glide.with(itemView.context).load(result.imageUrl).into(img)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context,DetailActivity::class.java)
                intent.putExtra("Car",result);
                itemView.context.startActivity(intent)
            }
        }

    }

}