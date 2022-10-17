package com.cowok.hijrah.challenge5.network

import com.cowok.hijrah.challenge5.model.GetAllGamesResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestfullApiGame {
    @GET("api/games")
    fun getAllGame(): Call<List<GetAllGamesResponseItem>>

    @GET("api/games/{title}")
    fun getDetailGame(@Path("title") title: String): Call<List<GetAllGamesResponseItem>>
}