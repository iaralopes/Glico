package com.example.iaralopes.glico.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Login (
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("password")
    @Expose
    var password: String
)