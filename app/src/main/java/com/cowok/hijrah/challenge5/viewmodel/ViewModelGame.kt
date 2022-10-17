package com.cowok.hijrah.challenge5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cowok.hijrah.challenge5.model.GetAllGamesResponseItem
import com.cowok.hijrah.challenge5.network.RetrofitGame
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelGame: ViewModel() {
    private lateinit var liveDataGame: MutableLiveData<List<GetAllGamesResponseItem>>

    var loading = MutableLiveData<Boolean>()

    init {
        liveDataGame = MutableLiveData()
    }

    fun getLiveDataGame(): MutableLiveData<List<GetAllGamesResponseItem>>{
        return liveDataGame
    }

    fun callApiGame(){
        GlobalScope.async {
            loading.postValue(true)
            RetrofitGame.instance.getAllGame()
                .enqueue(object : Callback<List<GetAllGamesResponseItem>>{
                    override fun onResponse(
                        call: Call<List<GetAllGamesResponseItem>>,
                        response: Response<List<GetAllGamesResponseItem>>
                    ) {
                        if (response.isSuccessful){
                            liveDataGame.postValue(response.body())
                        }else{
                            liveDataGame.postValue(null)
                        }
                        loading.postValue(false)
                    }

                    override fun onFailure(call: Call<List<GetAllGamesResponseItem>>, t: Throwable) {
                        liveDataGame.postValue(null)
                        loading.postValue(false)
                    }

                })
        }
    }

    fun callDetailApiGame(title: String){
        RetrofitGame.instance.getDetailGame(title)
            .enqueue(object : Callback<List<GetAllGamesResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetAllGamesResponseItem>>,
                    response: Response<List<GetAllGamesResponseItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataGame.postValue(response.body())
                    }else{
                        liveDataGame.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetAllGamesResponseItem>>, t: Throwable) {
                    liveDataGame.postValue(null)
                }

            })
    }
}