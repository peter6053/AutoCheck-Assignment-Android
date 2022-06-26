package com.autocheck.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.autocheck.R
import com.autocheck.model.CarMediaModel.CarMedia
import com.bumptech.glide.Glide

class CarMediaAdapter(
    private val mediaList: ArrayList<CarMedia>,
    private val mainImage: ImageView,
    private val mainVideo: VideoView
) : RecyclerView.Adapter<CarMediaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.car_media_item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(mediaList[position])
    }

    override fun getItemCount(): Int {
        return mediaList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindHolder(media: CarMedia) {
            val img = itemView.findViewById(R.id.thumbnail) as ImageView
            val playBtn = itemView.findViewById(R.id.play_btn) as ImageView
            Glide.with(itemView.context).load(media.url).into(img)
            if (media.type.contains("image") ||media.type.contains("png") || media.type.contains("jpg") || media.type.contains("jepg") || media.type.contains("webp")) {
                playBtn.visibility = View.GONE
            } else {
                playBtn.visibility = View.VISIBLE
            }
            itemView.setOnClickListener {
                if (media.type.contains("image") ||media.type.contains("png") || media.type.contains("jpg") || media.type.contains("jepg") || media.type.contains("webp")) {
                    mainImage.visibility = View.VISIBLE
                    mainVideo.visibility = View.GONE
                    Glide.with(itemView.context).load(media.url)
                        .into(mainImage)
                } else {
                    Log.e("URL",media.type+" "+media.url)
                    mainImage.visibility = View.GONE
                    mainVideo.visibility = View.VISIBLE
                    val uri: Uri = Uri.parse(media.url)
                    mainVideo.setVideoURI(uri)
                    mainVideo.start()
                }
            }
        }

    }

}