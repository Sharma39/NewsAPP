package com.example.newsappcc.view.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.geoloacationfinder.viewmodel.NewsViewModel
import com.example.newsappcc.R
import com.example.newsappcc.model.Articles
import com.example.newsappcc.view.adapter.NewsAdapter
import kotlinx.android.synthetic.main.home_fragment_layout.*
import java.util.*


class HomeFragment : Fragment(), NewsAdapter.NewsDelegate {

    //    private val newsAdapter = NewsAdapter(listOf())
    private val newsItemFragment: NewsItemFragment = NewsItemFragment()
//    var activity: Activity? = getActivity()

    private val viewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsAdapter = NewsAdapter(listOf(), this)
        //recycler Adapter
        news_recyclerview.adapter = newsAdapter



        viewModel.liveData.observe(viewLifecycleOwner, { list ->
            Log.d("TAG_J", list[0].toString())
            newsAdapter.updateNews(list)
        })

        viewModel.getNews(getCountry())

//        Log.d("TAG_C", getCountry())

    }

    private fun getCountry(): String {
        val locale = Locale.getDefault()
        val country = locale.country
        return country.lowercase(Locale.getDefault())
    }

    override fun selectNews(news: Articles) {
        //Bundle only to pass the data to fragment
        val bundle = Bundle()
        bundle.putSerializable("msg", news)
        newsItemFragment.setArguments(bundle)

        //To Open the Fragment we use supportFragmentManager
        activity?.let {
            it.supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )//main_frame is on activity_home_page
                .replace(R.id.main_frame, newsItemFragment)
                .addToBackStack(newsItemFragment.getTag())
                .commit()
        }

    }

    fun example() {

    }

}