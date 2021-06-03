package com.example.newsappcc.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.newsappcc.R
import com.example.newsappcc.view.adapter.StocksAdapter
import com.example.newsappcc.viewmodel.NewsSearchViewModel
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.stock_fragment_layout.*

class StocksFragment: Fragment() {

    private val vsModel: NewsSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stock_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottom_nav?.visibility = View.GONE

        val stocksAdapter = StocksAdapter(listOf())
        //recycler Adapter
        stnews_recyclerview.adapter = stocksAdapter



        vsModel.liveData.observe(viewLifecycleOwner, { list ->
            Log.d("TAG_J", list[0].toString())
            stocksAdapter.updateStocks(list)
        })

        vsModel.getSpecNews("stocks")

    }
}