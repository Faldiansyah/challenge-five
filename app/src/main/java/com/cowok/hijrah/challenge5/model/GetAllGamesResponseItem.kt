package com.cowok.hijrah.challenge5.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetAllGamesResponseItem(
    @SerializedName("author")
    val author: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("tag")
    val tag: String,
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("title")
    val title: String
): Serializable