package com.example.newsappcc.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.newsappcc.R
import com.example.newsappcc.view.adapter.PoliticsAdapter
import com.example.newsappcc.view.adapter.SportsAdapter
import com.example.newsappcc.viewmodel.NewsSearchViewModel
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.politics_fragment_layout.*
import kotlinx.android.synthetic.main.sports_fragment_layout.*
import kotlinx.android.synthetic.main.sports_fragment_layout.spnews_recyclerview

class PoliticsFragment: Fragment() {

    private val vpModel: NewsSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.politics_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottom_nav?.visibility = View.GONE

        val politicsAdapter = PoliticsAdapter(listOf())
        //recycler Adapter
        ppnews_recyclerview.adapter = politicsAdapter



        vpModel.liveData.observe(viewLifecycleOwner, { list ->
            Log.d("TAG_J", list[0].toString())
            politicsAdapter.updatePolitics(list)
        })

        vpModel.getSpecNews("congress")

    }
}