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

class SportsAdapter(private var sList: List<Articles>):
    RecyclerView.Adapter<SportsAdapter.SportsViewHolder>(){

    inner class SportsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    fun updateSports(nList: List<Articles>) {
        this.sList = nList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsViewHolder {
        val itemView  = LayoutInflater.from(parent.context).inflate(
            R.layout.sports_item_layout, parent, false
        )
        return SportsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        sList[position].let {
            holder.itemView.apply {
                sp_txt_textview.text = it.title
                sp_pub_textview.text = it.publishedAt.substring(0, 10)
                sp_sor_textview.text = it.source.name

            }
            Glide.with(holder.itemView)
                .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(it.urlToImage)
                .into(holder.itemView.sp_image_view)
        }

    }

    override fun getItemCount(): Int = sList.size
}