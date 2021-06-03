package com.example.newsappcc.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.geoloacationfinder.viewmodel.NewsViewModel
import com.example.newsappcc.R
import com.example.newsappcc.view.adapter.NewsAdapter
import com.example.newsappcc.view.adapter.SportsAdapter
import com.example.newsappcc.viewmodel.NewsSearchViewModel
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.home_fragment_layout.*
import kotlinx.android.synthetic.main.sports_fragment_layout.*

class SportsFragment: Fragment() {

    private val vsModel: NewsSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sports_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottom_nav?.visibility = View.GONE

        val sportsAdapter = SportsAdapter(listOf())
        //recycler Adapter
        spnews_recyclerview.adapter = sportsAdapter



        vsModel.liveData.observe(viewLifecycleOwner, { list ->
            Log.d("TAG_J", list[0].toString())
            sportsAdapter.updateSports(list)
        })

        vsModel.getSpecNews("sports")

    }
}