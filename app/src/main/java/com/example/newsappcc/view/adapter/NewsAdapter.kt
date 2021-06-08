package com.example.newsappcc.view.adapter

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsappcc.R
import com.example.newsappcc.SignupFragment
import com.example.newsappcc.model.Articles
import com.example.newsappcc.model.Favourite
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.news_item_layout.view.*


class NewsAdapter(private var newsList: List<Articles>, private val newsDelegate: NewsDelegate) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var click: Boolean = false
    private lateinit var imgurl: String


    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface NewsDelegate {
        fun selectNews(nws: Articles)
    }

    fun updateNews(nList: List<Articles>) {
        this.newsList = nList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
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
            // adding the url to global variable to add to firebase, no need of this I have made
            // the change below while uploading to firebase last line
            // imgurl = it.urlToImage
            val colorMatrix = ColorMatrix()
            colorMatrix.setSaturation(3f)
            val filter = ColorMatrixColorFilter(colorMatrix)
            holder.itemView.image_view.setColorFilter(filter)



            holder.itemView.setOnClickListener {
                newsDelegate.selectNews(newsList[position])
            }

            //star click
            holder.itemView.star_view.setOnClickListener { p ->
                if (!click) {
                    holder.itemView.star_view.setImageResource(R.drawable.ic_star_golden)
                    click = true

                    //uploading to firebase
                    val ref = FirebaseAuth.getInstance()
                        .currentUser?.let { it1 ->
                            FirebaseDatabase.getInstance().reference.child(
                                "User: ${it1.uid}"
                            ).child("NewsPosts")
                        }
                    val key = ref?.push()?.key ?: ""
                    val post = Favourite(
                        FirebaseAuth.getInstance().currentUser.toString(),
                        key,
                        holder.itemView.txt_textview.text.toString().trim(),
                        holder.itemView.pub_textview.text.toString().trim(),
                        it.urlToImage
                    )
                    ref?.child(key)?.setValue(post)
                }
                else if (click) {
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