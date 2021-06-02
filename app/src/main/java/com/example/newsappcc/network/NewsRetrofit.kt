package com.example.newsappcc.network

import android.util.Log
import com.example.geoloacationfinder.util.Constants.Companion.API_KEY
import com.example.newsappcc.model.Headlines
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class NewsRetrofit {


    private val BASE_URL = "https://newsapi.org/v2/"

    private val newsEndPoint = createRetrofit().create(NewsEndPoint::class.java)

    private fun createRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        // this line below only needed when using coroutine adapter
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    //Without coroutine only fun makeApiCall with no suspend and no Asynch at the end of name and no deferred
    suspend fun makeApiCallAsync(country: String): Deferred<Headlines> {
        Log.d("TAG_C", "$country in retrofit")
        return newsEndPoint.getHeadlinesAsync(
            country,
            API_KEY
        )
    }

    //https://newsapi.org/v2/everything?
// q=bitcoin&
// apiKey=17009f55c6204b4289ebca6b5dc2d253

    interface NewsEndPoint {
        //Endpoint
        @GET("top-headlines")
        fun getHeadlinesAsync(
            @Query("country") country: String,
            @Query("apiKey") apiKey: String
        ): Deferred<Headlines>
    }


    }