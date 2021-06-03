package com.example.newsappcc.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsappcc.R
import com.example.newsappcc.model.Articles
import kotlinx.android.synthetic.main.stock_item_layout.view.*

class StocksAdapter(private var stList: List<Articles>):
    RecyclerView.Adapter<StocksAdapter.StocksViewHolder>(){

    inner class StocksViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    fun updateStocks(nList: List<Articles>) {
        this.stList = nList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksViewHolder {
        val itemView  = LayoutInflater.from(parent.context).inflate(
            R.layout.stock_item_layout, parent, false
        )
        return StocksViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StocksViewHolder, position: Int) {
        stList[position].let {
            holder.itemView.apply {
                st_txt_textview.text = it.title
                st_pub_textview.text = it.publishedAt.substring(0, 10)
                st_sor_textview.text = it.source.name

            }
            Glide.with(holder.itemView)
                .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(it.urlToImage)
                .into(holder.itemView.st_image_view)
        }

    }

    override fun getItemCount(): Int = stList.size
}