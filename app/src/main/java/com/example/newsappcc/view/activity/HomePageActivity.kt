package com.example.newsappcc.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.newsappcc.R
import com.example.newsappcc.view.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePageActivity : AppCompatActivity() {

    private lateinit var adapter: MainViewPagerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        adapter = MainViewPagerAdapter(supportFragmentManager)
        main_viewpager.adapter = adapter


        //View Pager
        main_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //Do nothing...
            }
            //Selecting bottom menu based on page selected
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> bottom_nav.selectedItemId = R.id.home_item
                    1 -> bottom_nav.selectedItemId = R.id.search_item
                    else -> bottom_nav.selectedItemId = R.id.favourite_item
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                //Do nothing
            }

        })

        bottom_nav.setOnNavigationItemSelectedListener {
            // Selecting page based on bottom menu selected
            when (it.itemId) {
                R.id.home_item -> openFragment(0)
                R.id.search_item -> openFragment(1)
                else -> openFragment(2)
            }
            true
        }

    }

    private fun openFragment(frag: Int) {
        main_viewpager.setCurrentItem(frag, true)
    }

    }