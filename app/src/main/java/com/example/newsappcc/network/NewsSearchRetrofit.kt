package com.example.newsappcc.network

import android.util.Log
import com.example.geoloacationfinder.util.Constants
import com.example.newsappcc.model.Headlines
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class NewsSearchRetrofit {
    private val BASE_URL = "https://newsapi.org/v2/"

    private val snewsEndPoint = createRetrofit().create(SearchNewsEndPoint::class.java)

    private fun createRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        // this line below only needed when using coroutine adapter
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    //Without coroutine only fun makeApiCall with no suspend and no Asynch at the end of name and no deferred
    suspend fun mApiCallAsync(query: String): Deferred<Headlines> {
        Log.d("TAG_J", "$query in retrofit")
        return snewsEndPoint.getSpecificdataAsync(
            query,
            Constants.API_KEY
        )
    }

//https://newsapi.org/v2/everything?
// q=bitcoin&
// apiKey=17009f55c6204b4289ebca6b5dc2d253
    interface SearchNewsEndPoint {

        //Endpoint
        @GET("everything")
        fun getSpecificdataAsync(
            @Query("q") query: String,
            @Query("apiKey") apiKey: String
        ): Deferred<Headlines>
    }
}