package com.example.newsappcc.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsappcc.R
import com.example.newsappcc.model.Articles
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.news_item_fragment_layout.*

class NewsItemFragment: Fragment() {

    private lateinit var nInterface: NewInterface1


    interface NewInterface1 {
        fun example()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_item_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottom_nav?.visibility = View.GONE

        val new1: Articles? = arguments?.getSerializable("msg") as Articles?
        if (new1 != null) {
            (t_text_view as TextView).text = new1.title
            (view.findViewById<View>(R.id.des_view) as TextView).text = new1.description
            (view.findViewById<View>(R.id.src_view) as TextView).text = new1.source.name
            (view.findViewById<View>(R.id.author_view) as TextView).text = "BY: ${new1.author}"


            Glide.with(view)
                .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(new1.urlToImage)
                .into(i_img_view)


        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        nInterface = context as NewInterface1
//        nInterface!!.example()
    }
}