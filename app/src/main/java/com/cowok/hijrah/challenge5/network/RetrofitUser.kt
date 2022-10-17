package com.cowok.hijrah.challenge5.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUser {
    const val BASE_URL = "https://6254434289f28cf72b5aed04.mockapi.io/"

    val instance : RestfullApiUser by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RestfullApiUser::class.java)
    }
}