package com.cowok.hijrah.challenge5.model

import java.io.Serializable

data class UserModel(
    var username: String,
    var name: String,
    var password: String
): Serializable