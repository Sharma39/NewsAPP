package com.example.newsappcc.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsappcc.R
import com.example.newsappcc.model.Articles
import kotlinx.android.synthetic.main.politics_item_layout.view.*
import kotlinx.android.synthetic.main.sports_item_layout.view.*

class PoliticsAdapter(private var pList: List<Articles>):
    RecyclerView.Adapter<PoliticsAdapter.PoliticsViewHolder>() {

    inner class PoliticsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun updatePolitics(nList: List<Articles>) {
        this.pList = nList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoliticsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.politics_item_layout, parent, false
        )
        return PoliticsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PoliticsViewHolder, position: Int) {
        pList[position].let {
            holder.itemView.apply {
                pp_txt_textview.text = it.title
                pp_pub_textview.text = it.publishedAt.substring(0, 10)
                pp_sor_textview.text = it.source.name

            }
            Glide.with(holder.itemView)
                .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(it.urlToImage)
                .into(holder.itemView.pp_image_view)
        }

    }

    override fun getItemCount(): Int = pList.size
}