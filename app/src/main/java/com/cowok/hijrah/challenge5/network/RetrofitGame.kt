package com.cowok.hijrah.challenge5.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitGame {
    const val BASE_URL = "https://the-lazy-media-api.vercel.app/"

    val instance: RestfullApiGame by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RestfullApiGame::class.java)
    }
}