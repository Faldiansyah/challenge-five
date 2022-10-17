package com.cowok.hijrah.challenge5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cowok.hijrah.challenge5.model.GetAllUserResponseItem
import com.cowok.hijrah.challenge5.model.UserModel
import com.cowok.hijrah.challenge5.network.RetrofitUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser : ViewModel() {
    private lateinit var liveDataUser: MutableLiveData<List<GetAllUserResponseItem>>
    private lateinit var postLDUser: MutableLiveData<GetAllUserResponseItem>

    init {
        liveDataUser = MutableLiveData()
        postLDUser = MutableLiveData()
    }

    fun ambilLiveDataUser() : MutableLiveData<List<GetAllUserResponseItem>> {
        return liveDataUser
    }

    fun addLiveDataUser() : MutableLiveData<GetAllUserResponseItem> {
        return postLDUser
    }

    fun callPostApiCar(name: String, username: String, password: String){
        RetrofitUser.instance.addUser(UserModel(name, username, password))
            .enqueue(object : Callback<GetAllUserResponseItem>{
                override fun onResponse(
                    call: Call<GetAllUserResponseItem>,
                    response: Response<GetAllUserResponseItem>
                ) {
                    if (response.isSuccessful){
                        postLDUser.postValue(response.body())
                    }else{
                        postLDUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<GetAllUserResponseItem>, t: Throwable) {
                    postLDUser.postValue(null)
                }

            })
    }

    fun callApiUser(){
        RetrofitUser.instance.getAllUser()
            .enqueue(object : Callback<List<GetAllUserResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetAllUserResponseItem>>,
                    response: Response<List<GetAllUserResponseItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataUser.postValue(response.body())
                    }else{
                        liveDataUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetAllUserResponseItem>>, t: Throwable) {
                    liveDataUser.postValue(null)
                }

            })
    }

    fun requestLogin(id: Int, name: String, username: String, password: String) {
        RetrofitUser.instance.putUser(id, UserModel(name, username, password))
            .enqueue(object : Callback<List<GetAllUserResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetAllUserResponseItem>>,
                    response: Response<List<GetAllUserResponseItem>>
                ) {
                    liveDataUser.postValue(response.body())
                }

                override fun onFailure(call: Call<List<GetAllUserResponseItem>>, t: Throwable) {
                    liveDataUser.postValue(null)
                }

            })
    }
}