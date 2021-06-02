package com.example.newsappcc.view.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.geoloacationfinder.util.State
import com.example.newsappcc.R
import com.example.newsappcc.view.adapter.SearchAdapter
import com.example.newsappcc.viewmodel.NewsSearchViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.search_fragment_layout.*


class NewsSearchFragment : Fragment() {


var activity: Activity? = getActivity()

    private val vModel: NewsSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchAdapter = SearchAdapter(listOf())
        //recycler Adapter
        search_recyclerview.adapter = searchAdapter


          vModel.liveData.observe(viewLifecycleOwner, { list ->
                Log.d("TAG_J", list[0].toString())
                searchAdapter.updateSearchNews(list)

        })

        edit_text.setOnClickListener{
            it.showKeyboard()
        }


        img_view.setOnClickListener {
            val value: String = edit_text.text.toString().trim().lowercase()

            it.hideKeyboard()

            if (value.isEmpty()) {
                Toast.makeText(getActivity(), "Nothing Entered", Toast.LENGTH_SHORT).show()
            }
            else {
                Log.d("TAG_V", value)
                vModel.getSpecNews(value)
            }
        }

        getActivity()?.let {
            vModel.statusData.observe(it, {
                when (it) {
                    State.LOADING -> status_progressbar.visibility = View.VISIBLE
                    State.ERROR -> displayError()
                    else -> status_progressbar.visibility = View.GONE
                }
            })
        }
    }

    private fun displayError() {
        status_progressbar.visibility = View.GONE
        Toast.makeText(getActivity(), "An Error Occurred", Toast.LENGTH_SHORT).show()
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInputFromInputMethod(windowToken, 0)
    }
}
