package com.example.newsappcc.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsappcc.R
import com.example.newsappcc.model.Favourite
import kotlinx.android.synthetic.main.favourite_fragment_layout.view.*
import kotlinx.android.synthetic.main.favourite_item_layout.view.*
import kotlinx.android.synthetic.main.news_item_layout.view.*

class FavouriteAdapter(): RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    var nposts: List<Favourite> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class FavouriteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val itemView  = LayoutInflater.from(parent.context).inflate(
            R.layout.favourite_item_layout, parent, false
        )
        return FavouriteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val post = nposts[position]
        holder.itemView.f_title.text = post.title
        holder.itemView.f_publish.text = post.publishAt
        Glide.with(holder.itemView)
            .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
            .load(post.url)
            .into(holder.itemView.f_img)
    }

    override fun getItemCount(): Int = nposts.size
}