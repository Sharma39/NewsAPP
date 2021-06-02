package com.example.newsappcc.view.adapter

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsappcc.R
import com.example.newsappcc.model.Articles
import com.example.newsappcc.view.fragment.NewsItemFragment
import kotlinx.android.synthetic.main.news_item_layout.view.*


class NewsAdapter(private var newsList: List<Articles>, private val newsDelegate: NewsDelegate) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var click: Boolean = false

    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    interface NewsDelegate {
        fun selectNews(pokemon: Articles)
    }

    fun updateNews(nList: List<Articles>) {
        this.newsList = nList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView  = LayoutInflater.from(parent.context).inflate(
            R.layout.news_item_layout, parent, false
        )
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        newsList[position].let {
            holder.itemView.apply {
                txt_textview.text = it.title
                pub_textview.text = it.publishedAt.substring(0, 10)
                sor_textview.text = it.source.name

            }
            Glide.with(holder.itemView)
                .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(it.urlToImage)
                .into(holder.itemView.image_view)
            val colorMatrix = ColorMatrix()
            colorMatrix.setSaturation(3f)
            val filter = ColorMatrixColorFilter(colorMatrix)
            holder.itemView.image_view.setColorFilter(filter)



            holder.itemView.setOnClickListener {
                newsDelegate.selectNews(newsList[position])
            }

            holder.itemView.star_view.setOnClickListener{
                    if(!click) {
                        holder.itemView.star_view.setImageResource(R.drawable.ic_star_golden)
                        click = true
                    }
                else if(click){
                        holder.itemView.star_view.setImageResource(R.drawable.ic_star_white)
                        click = false
                    }
                }


        }
    }


    override fun getItemCount(): Int {
        Log.d("TAG_N", newsList.size.toString())
      return newsList.size
    }


}