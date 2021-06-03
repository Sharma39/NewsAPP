package com.example.geoloacationfinder.viewmodel



import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geoloacationfinder.util.State
import com.example.newsappcc.network.NewsRetrofit
import com.example.newsappcc.model.Articles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {


    private var netJob: Job? = null

    private val retrofit = NewsRetrofit()

    val liveData: MutableLiveData<List<Articles>> = MutableLiveData()

    val statusData = MutableLiveData<State>()

    fun getNews(country: String){
//        statusData.value = State.LOADING
//        Log.d("TAG_C", "$country my home")
        netJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = retrofit.makeApiCallAsync(
                    country
                ).await()


                liveData.postValue(result.articles)
//                statusData.postValue(State.SUCCESS)

            } catch (e: Exception) {
                //At this point an error occurred
                Log.d("TAG_E", e.message.toString())
//                statusData.postValue(State.ERROR)
            }

        }

    }

    override fun onCleared() {
        netJob?.cancel()
        super.onCleared()
    }
}