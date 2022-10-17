package com.cowok.hijrah.challenge5.network

import com.cowok.hijrah.challenge5.model.GetAllGamesResponseItem
import com.cowok.hijrah.challenge5.model.GetAllUserResponseItem
import com.cowok.hijrah.challenge5.model.UserModel
import retrofit2.Call
import retrofit2.http.*

interface RestfullApiUser {
    @GET("user")
    fun getAllUser(): Call<List<GetAllUserResponseItem>>

    @POST("user")
    fun addUser(@Body request: UserModel): Call<GetAllUserResponseItem>

    @PUT("user/{id}")
    fun putUser(@Path("id") id: Int, @Body request: UserModel): Call<List<GetAllUserResponseItem>>
}