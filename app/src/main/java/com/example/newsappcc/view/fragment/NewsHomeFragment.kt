package com.example.newsappcc.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.geoloacationfinder.viewmodel.NewsViewModel
import com.example.newsappcc.R
import com.example.newsappcc.SignInActivity
import com.example.newsappcc.model.Articles
import com.example.newsappcc.view.adapter.NewsAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.news_home_fragment_layout.*
import java.util.*


class NewsHomeFragment : Fragment(), NewsAdapter.NewsDelegate {

    //    private val newsAdapter = NewsAdapter(listOf())
    private val newsItemFragment: NewsItemFragment = NewsItemFragment()

    //    var activity: Activity? = getActivity()


    private val viewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_home_fragment_layout, container, false)
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

        // Log.d("TAG_C", getCountry())
        viewModel.getNews(getCountry())



        //Menu
        menu_imageview.setOnClickListener {
            val menu = PopupMenu(activity, it)
            val popUpInflater = menu.menuInflater
            popUpInflater.inflate(
                R.menu.menu_window,
                menu.menu
            )
            menu.setOnMenuItemClickListener { menuIt ->

                when (menuIt.itemId) {
                    R.id.sports -> {
                        openFragment(SportsFragment())
                    }
                    R.id.politics -> {
                        openFragment(PoliticsFragment())
                    }
                    R.id.weather -> {
                        openFragment(WeatherFragment())
                    }
                    R.id.stocks -> {
                        openFragment(StocksFragment())
                    }
                    R.id.logout -> {
                        logout()
                    }
                }

                true
            }
            menu.show()
        }

    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()

        activity?.let {
            val intent = Intent(it, SignInActivity::class.java)
            it.startActivity(intent)
            it.finishAffinity()
        }



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

    private fun openFragment(frag: Fragment) {

        val transaction = activity?.supportFragmentManager?.beginTransaction()
            ?.addToBackStack(frag.tag)
            ?.replace(R.id.main_frame, frag)
            ?.commit()
    }

    fun example() {

    }

}