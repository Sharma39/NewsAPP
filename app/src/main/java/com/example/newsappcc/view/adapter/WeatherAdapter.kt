package com.example.newsappcc.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsappcc.R
import com.example.newsappcc.model.Articles
import kotlinx.android.synthetic.main.sports_item_layout.view.*
import kotlinx.android.synthetic.main.weather_item_layout.view.*

class WeatherAdapter(private var wList: List<Articles>):
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>(){

    inner class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    fun updateWeather(nList: List<Articles>) {
        this.wList = nList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView  = LayoutInflater.from(parent.context).inflate(
            R.layout.weather_item_layout, parent, false
        )
        return WeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        wList[position].let {
            holder.itemView.apply {
                wp_txt_textview.text = it.title
                wp_pub_textview.text = it.publishedAt.substring(0, 10)
                wp_sor_textview.text = it.source.name

            }
            Glide.with(holder.itemView)
                .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(it.urlToImage)
                .into(holder.itemView.wp_image_view)
        }

    }

    override fun getItemCount(): Int = wList.size
}