package com.example.newsappcc.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsappcc.R
import com.example.newsappcc.model.Favourite
import com.example.newsappcc.view.adapter.FavouriteAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.favourite_fragment_layout.*

class FavouriteFragment: Fragment() {

    private val favAdapter = FavouriteAdapter()
    private val postReference = FirebaseAuth.getInstance().currentUser?.uid?.let {
        Firebase.database.reference
        .child("User: $it").child("NewsPosts")
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.favourite_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fav_recyclerview.adapter = favAdapter

        //getting data from firebase
        postReference?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val newsList = mutableListOf<Favourite>()
                snapshot.children.forEach {
                    it.getValue(Favourite::class.java)?.let { item ->
                        newsList.add(item)
                    }
                }
                favAdapter.nposts = newsList
            }

            override fun onCancelled(error: DatabaseError) {
                //we never make mistakes..
            }
        })
    }

}