package com.example.newsappcc.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.newsappcc.R
import com.example.newsappcc.view.adapter.SportsAdapter
import com.example.newsappcc.view.adapter.WeatherAdapter
import com.example.newsappcc.viewmodel.NewsSearchViewModel
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.sports_fragment_layout.*
import kotlinx.android.synthetic.main.weather_fragment_layout.*

class WeatherFragment: Fragment() {
    private val vsModel: NewsSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottom_nav?.visibility = View.GONE

        val weatherAdapter = WeatherAdapter(listOf())
        //recycler Adapter
        wpnews_recyclerview.adapter = weatherAdapter



        vsModel.liveData.observe(viewLifecycleOwner, { list ->
            Log.d("TAG_J", list[0].toString())
            weatherAdapter.updateWeather(list)
        })

        vsModel.getSpecNews("weather")

    }
}