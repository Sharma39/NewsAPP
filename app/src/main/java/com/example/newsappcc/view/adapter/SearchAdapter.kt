package com.example.newsappcc.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsappcc.R
import com.example.newsappcc.model.Articles
import kotlinx.android.synthetic.main.search_item_layout.view.*

class SearchAdapter(private var sList: List<Articles>):
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    fun updateSearchNews(nList: List<Articles>) {
        this.sList = nList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.SearchViewHolder {
        val itemView  = LayoutInflater.from(parent.context).inflate(
            R.layout.search_item_layout, parent, false
        )
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        sList[position].let {
            holder.itemView.apply {
                txt1_textview.text = it.title
                pub1_textview.text = it.publishedAt.substring(0, 10)
                sor1_textview.text = it.source.name
            }
            Glide.with(holder.itemView)
                .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(it.urlToImage)
                .into(holder.itemView.image1_view)
//            val colorMatrix = ColorMatrix()
//            colorMatrix.setSaturation(1f)
//            val filter = ColorMatrixColorFilter(colorMatrix)
//            holder.itemView.image_view.setColorFilter(filter)
        }
    }

    override fun getItemCount(): Int = sList.size
}