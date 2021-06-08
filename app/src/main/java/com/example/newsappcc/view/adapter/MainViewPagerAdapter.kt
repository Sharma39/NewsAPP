package com.example.newsappcc.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newsappcc.view.fragment.FavouriteFragment
import com.example.newsappcc.view.fragment.NewsHomeFragment
import com.example.newsappcc.view.fragment.NewsSearchFragment

class MainViewPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> NewsHomeFragment()
//            0 -> AppFragment.getInstance(AppFragment.NewsFragment.HOME)
            1 -> NewsSearchFragment()
            else -> FavouriteFragment()
        }
    }
}